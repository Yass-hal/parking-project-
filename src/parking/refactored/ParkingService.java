package parking.refactored;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParkingService {

    private final Logger logger;
    private final ReportFormatter formatter;
    private final Map<String,Tariff> tariffs;
    private final List<String> receipts=new ArrayList<>();
    private double total=0;

    public ParkingService(Logger logger,ReportFormatter formatter,Map<String,Tariff> tariffs){
        this.logger=logger;
        this.formatter=formatter;
        this.tariffs=tariffs;
    }

    public double fee(Vehicle vehicle,int hours){
        Tariff tariff=tariffs.get(vehicle.getType());

        if(tariff==null){
            throw new IllegalArgumentException("Unknown vehicle type");
        }

        return tariff.calculateFee(hours);
    }

    public void checkout(Vehicle vehicle,int hours,PaymentMethod method){
        double amount=fee(vehicle,hours);

        method.pay(amount);
        total+=amount;

        String receipt=formatter.formatReceipt(vehicle,hours,amount,method);
        receipts.add(receipt);
        logger.log(receipt);
    }

    public void cancel(Vehicle vehicle,int hours,RefundablePayment method){
        double amount=fee(vehicle,hours);

        method.refund(amount);
        total-=amount;
    }

    public void cancelAtDesk(Vehicle vehicle,int hours,CashPayment method){
        double amount=fee(vehicle,hours);

        System.out.println("Refund "+amount+" MAD at the desk for "+vehicle.getPlate());
        total-=amount;
    }

    public String dailyReport(){
        return formatter.formatDailyReport(receipts,total);
    }

    public double getTotal(){
        return total;
    }

}
