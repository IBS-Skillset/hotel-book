package com.happystays.book.query.api.queries;

import com.happystays.book.query.domain.MyTripsResponseDto;
import com.happystays.book.query.repository.PnrRepository;
import com.happystays.cqrs.core.domain.BaseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelQueryHandler implements QueryHandler {
    private final PnrRepository pnrRepository;

    public HotelQueryHandler(PnrRepository pnrRepository) {
        this.pnrRepository = pnrRepository;
    }

    @Override
    public List<BaseEntity> handle(FindMyTrips query) {
        Date d = Date.from(LocalDate.now().minusMonths(6).atStartOfDay(ZoneId.systemDefault()).toInstant());
        var result = pnrRepository.findByUserIdAndCreationDateAfter(Long.valueOf(query.getId()), d);
        if (result.isEmpty()) {
            return null;
        }
        List<MyTripsResponseDto> responseDto = result.stream()
                .flatMap(pnr -> pnr.getHotelInfo().stream()
                .flatMap(info -> info.getSegment().stream()
                .map(seg -> new MyTripsResponseDto(seg.getConfirmationNumber(),
                        pnr.getCreationDate().toString(), info.getPropertyName(),
                        seg.getCheckInDate().toString(), seg.getCheckOutDate().toString(),
                        String.valueOf(seg.getHotelPrice()), seg.getCurrencyCode(),
                        seg.getHotelCancellationInfo().getCancellationDeadline().toString()))))
                .sorted(Comparator.comparing(MyTripsResponseDto::getCheckInDate))
                .collect(Collectors.toList());
        return new ArrayList<>(responseDto);
    }
}
