package parking.refactored;

import java.util.Map;

public class ParkingServiceTest {
    public static void main(String[] args){
        Logger fakeLogger=new Logger(){
            @Override
            public void log(String message){
            }
        };

        ReportFormatter formatter=new ReportFormatter();

        Map<String,Tariff> tariffs=Map.of(
                "CAR",new CarTariff(),
                "MOTORBIKE",new MotorbikeTariff(),
                "TRUCK",new TruckTariff()
        );

        ParkingService garage=new ParkingService(fakeLogger,formatter,tariffs);

        Vehicle car=new Vehicle("12345-A-6","CAR");
        Vehicle bike=new Vehicle("67890-B-1","MOTORBIKE");
        Vehicle truck=new Vehicle("24680-D-8","TRUCK");

        System.out.println("Car fee: "+garage.fee(car,3));
        System.out.println("Motorbike fee: "+garage.fee(bike,2));
        System.out.println("Truck fee: "+garage.fee(truck,4));

        CardPayment card=new CardPayment("4242");
        CashPayment cash=new CashPayment();

        garage.checkout(car,3,card);
        garage.checkout(bike,2,cash);
        garage.checkout(truck,4,card);

        garage.cancelAtDesk(bike,2,cash);
        garage.cancel(truck,4,card);

        System.out.println(garage.dailyReport());
    }
}
