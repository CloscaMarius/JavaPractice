package teme.w02_flow;

class Ex3_FinancialSimulation {

    static int simulate(double initial, double interestPct, double target) {

        System.out.printf("\nSimulation started, with parameters: initial: %.2f, interest: %.2f%%, target: %.2f\n",
                initial, interestPct, target);

        double initialAmount = initial;
        double interest = initialAmount * interestPct / 100;
        double totalAmount = initial + interest;

        if (initial >= target) {
            return 0;
        }
        int year = 1;

        for (; totalAmount < target; ) {
            System.out.println("Year " + year + " :amount " + String.format("%.2f", initialAmount) + " interest: " + String.format("%.2f", initialAmount * interestPct / 100) + " => total amount " + String.format("%.2f", totalAmount));
            initialAmount += initialAmount * interestPct / 100;
            totalAmount = initialAmount + initialAmount * interestPct / 100;
            year++;
        }
        System.out.println("Year " + year + " :amount " + String.format("%.2f", initialAmount) + " interest: " + String.format("%.2f", initialAmount * interestPct / 100) + " => total amount " + String.format("%.2f", totalAmount));
        System.out.println("Can happily retire in: " + year + " years!");
        return year;
    }

    public static void main(String[] args) {
        simulate(1000, 10, 900);
        simulate(1000, 10, 1000);
        simulate(1000, 10, 1100);
        simulate(1000, 10, 1600);

        simulate(1000, 2.5, 1500);
        simulate(1000, 0.1, 1500);
    }
}
