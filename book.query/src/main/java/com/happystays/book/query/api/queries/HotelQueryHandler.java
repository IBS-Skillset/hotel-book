package com.happystays.book.query.api.queries;

import com.happystays.book.query.domain.MyTripsResponseDto;
import com.happystays.book.query.repository.PnrRepository;
import com.happystays.cqrs.core.domain.BaseEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HotelQueryHandler implements QueryHandler {
    private final PnrRepository pnrRepository;
    public HotelQueryHandler(PnrRepository pnrRepository) {
        this.pnrRepository = pnrRepository;
    }

    @Value("${my-trips.from.month}")
    private String fromMonth;

    @Override
    public List<BaseEntity> handle(FindMyTrips query) {
        Date fromDate = Date.from(LocalDate.now().minusMonths(Long.valueOf(fromMonth)).atStartOfDay(ZoneId.systemDefault()).toInstant());
        var result = pnrRepository.findByUserIdAndCreationDateAfter(Long.valueOf(query.getId()), fromDate);
        if (result.isEmpty()) {
            return Collections.emptyList();
        }
        return result.stream()
                .flatMap(pnr -> pnr.getHotelInfo().stream()
                .flatMap(info -> info.getSegment().stream()
                .map(seg -> new MyTripsResponseDto(seg.getConfirmationNumber(),
                        pnr.getCreationDate(), info.getPropertyName(),
                        seg.getCheckInDate(), seg.getCheckOutDate(),
                        seg.getHotelPrice(), seg.getCurrencyCode(),
                        seg.getHotelCancellationInfo().getCancellationDeadline()))))
                .sorted(Comparator.comparing(MyTripsResponseDto::getCheckInDate))
                .collect(Collectors.toList());
    }
}
