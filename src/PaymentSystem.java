
public class PaymentSystem {
    //Variables
    static PaymentSystem obj;

    //Member Functions
    private PaymentSystem(){

    }

    public PaymentSystem getPaymentSystem(){
        if (obj == null){
            obj = new PaymentSystem();
        }
        return obj;
    }

    //Getters


    //Setters
    
}
