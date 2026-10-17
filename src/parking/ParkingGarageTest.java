package parking;

import java.io.IOException;

public class ParkingGarageTest {
    public static void main(String[] args) throws IOException {
        ParkingGarage garage=new ParkingGarage();
        Vehicle truck=new Vehicle("24680-D-8","TRUCK");
        PaymentMethod card=new CardPayment("4242");

        double fee=garage.fee(truck,4);

        System.out.println("Truck fee: "+fee+" MAD");

        if(fee!=60.0){
            throw new AssertionError("Incorrect truck fee");
        }

        garage.checkout(truck,4,card);

        System.out.println("Test passed");
    }
}
