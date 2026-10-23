package parking.refactored;

public class MobileWalletPayment implements RefundablePayment {
    @Override
    public String name(){
        return "MOBILE WALLET";
    }

    @Override
    public void pay(double amount){
        System.out.println("[MOBILE WALLET] paid "+amount+" MAD");
    }

    @Override
    public void refund(double amount){
        System.out.println("[MOBILE WALLET] refunded "+amount+" MAD");
    }
}
