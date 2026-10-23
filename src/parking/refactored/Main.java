package parking.refactored;

import java.util.Map;

public class Main {

    public static void main(String[] args){
        //Logger logger=new FileLogger("parking.log");
        Logger logger=new DatabaseLogger();
        //ReportFormatter formatter=new ReportFormatter();
        ReportFormatter formatter=new CsvReportFormatter();

        Map<String,Tariff> tariffs=Map.of(
                "CAR",new CarTariff(),
                "MOTORBIKE",new MotorbikeTariff(),
                "TRUCK",new TruckTariff(),
                "BUS",new BusTariff()
        );

        ParkingService garage=new ParkingService(logger,formatter,tariffs);

        Vehicle car=new Vehicle("12345-A-6","CAR");
        Vehicle bike=new Vehicle("67890-B-1","MOTORBIKE");
        Vehicle truck=new Vehicle("24680-D-8","TRUCK");
        Vehicle bus=new Vehicle("11111-A-1","BUS");

        CardPayment card=new CardPayment("4242");
        CashPayment cash=new CashPayment();
        MobileWalletPayment wallet=new MobileWalletPayment();

        garage.checkout(car,3,card);
        garage.checkout(bike,2,cash);
        garage.checkout(truck,4,card);

        garage.checkout(bus,2,wallet);
        garage.cancel(bus,2,wallet);

        garage.cancelAtDesk(bike,2,cash);
        garage.cancel(truck,4,card);


        System.out.println(garage.dailyReport());
    }

}
