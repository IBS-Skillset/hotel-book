//package com.happystays.book.query.repository;
//
//import com.happystays.book.query.domain.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//import java.time.ZoneId;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//
///**
// * for testing purpose
// */
//@Component
//public class DataLoader implements ApplicationRunner {
//
//    private final UserRepository userRepository;
//
//    private final PnrRepository pnrRepository;
//
//    @Autowired
//    public DataLoader(UserRepository userRepository, PnrRepository pnrRepository) {
//        this.userRepository = userRepository;
//        this.pnrRepository = pnrRepository;
//    }
//
//
//    public void run(ApplicationArguments args) throws Exception {
//
//        User user = new User();
//        user.setId(1);
//        user.setEmail("test@gmail.com");
//        user.setFirstName("TestFirstName");
//        user.setLastName("TestLastName");
//        user.setRole("USER");
//        user.setPassword("$2a$10$WGdJk7Ls2x0Pi.R3zU8nW.EDOP3kNd8XWr6TJfto.ljF6BIrlo22a");//test
//       // user.setPassword("test");
//        userRepository.save(user);
//
//
//    //    Pnr pnr = new Pnr();
//        User user1 = userRepository.findByEmail("test@gmail.com");
//
//        Date d;
////                = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
//        List<Pnr> pnrList=new ArrayList<>();
//
//        Trip trip=new Trip();
//        trip.setTotalPrice(Double.parseDouble("1234.324"));
//
//        for (int i=0;i<15;i++) {
//
//
//            Pnr pnr = new Pnr();
//            Date upcomming = Date.from(LocalDate.now().plusDays(10+1).atStartOfDay(ZoneId.systemDefault()).toInstant());
//            Date completed  = Date.from(LocalDate.now().minusDays(10+i).atStartOfDay(ZoneId.systemDefault()).toInstant());
//            d= i%2==0?  upcomming : completed;
//            pnr.setCreationDate(d);
//            Supplier supplier = new Supplier();
////            supplier.setSupplierId(1);
//            HotelRoomInfo roomInfo = new HotelRoomInfo();
//            roomInfo.setBreakfastIncluded(true);
//
//            HotelCancellationInfo hotelCancellationInfo = new HotelCancellationInfo();
//            hotelCancellationInfo.setCancellationDeadline(d);
//
//
//            HotelSegment hotelSegment = new HotelSegment();
////            hotelSegment.setHotelSegmentId(7);
//            hotelSegment.setCheckInDate(d);
//            hotelSegment.setCheckOutDate(d);
//            hotelSegment.setHotelPrice(1234);
//            hotelSegment.setCurrencyCode("EUR");
//            hotelSegment.setHotelRoomInfo(roomInfo);
//            hotelSegment.setConfirmationNumber("confimationNumber"+i);
//            hotelSegment.setHotelCancellationInfo(hotelCancellationInfo);
//
//            HotelSegment hotelSegment1 = new HotelSegment();
//            hotelSegment1.setCheckInDate(d);
//            hotelSegment1.setCheckOutDate(d);
//            hotelSegment1.setHotelPrice(12345);
//            hotelSegment1.setCurrencyCode("AUD");
//            hotelSegment1.setHotelRoomInfo(roomInfo);
//            hotelSegment1.setConfirmationNumber("confimationNumber2"+1);
//            hotelSegment1.setHotelCancellationInfo(hotelCancellationInfo);
//
//            HotelInfo hotelInfo = new HotelInfo();
//            hotelInfo.setCountryCode("91");
//
//            hotelInfo.setSegment(Arrays.asList(hotelSegment, hotelSegment1));
//            hotelInfo.setPropertyName("Hotel Name "+i);
//            hotelInfo.setHotelAddress("Hotel Address "+i);
//            hotelInfo.setTotalPrice(1343);
//            hotelInfo.setCurrencyCode("EUR");
//
//
//            pnr.setSupplier(supplier);
//            pnr.setTrip(trip);
//            pnr.setHotelInfo(Arrays.asList(hotelInfo));
//            pnr.setUser(Arrays.asList(user1));
//
//            pnrList.add(pnr);
//
//
//        }
//
//        pnrRepository.saveAll(pnrList);
//        //.getPnrId();
//    }}