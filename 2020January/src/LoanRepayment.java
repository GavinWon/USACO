import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.*;

public class LoanRepayment {
	static void println(String format, Object... args) { System.out.println(String.format(format, args)); }
	static void print(String format, Object... args) { System.out.print(String.format(format, args)); }

	private static long N, K, M;
	private static ArrayList<Long> payments;

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new FileReader(new File("loan.in")));
		PrintWriter out = new PrintWriter(new File("loan.out"));

		N = in.nextLong();
		K = in.nextLong();
		M = in.nextLong();

		long maxLoop = 1000000;

		println("10: %d", totalPaid(N, 10, maxLoop));
		println("11: %d", totalPaid(N, 11, maxLoop));


		long left = 1; long right = N;
		int i = 0;
		while (left <= right) {
			long middle = (left + right) / 2;
			long payment = totalPaid(N, middle, maxLoop);
			System.out.println();
			println("i: %d, X: %d, payment: %d", ++i, middle, payment);
			if (payment < N) {
				right = middle - 1;
			} else {
				left = middle + 1;
			}
			System.out.print("Payments: ");
			for (long i1 : payments) {
				System.out.print(i1 + ", ");
			}
			System.out.println();
		}

		long X = 0;
		if (totalPaid(N, left, maxLoop) >= N) {
			X= left;
		} else {
			X = right;
		}
		// if (N > 100000) X--;
		out.print(X);
		in.close();
		out.close();
	}

	public static long totalPaid(long balance, long X, long maxLoop) {
		long originalBal = balance;
		maxLoop = Math.min(maxLoop, K);
		payments = new ArrayList<Long>();
		long total = 0;
		long i;
		for (i = 0; i < maxLoop; i++) {
			long payment = (originalBal - total) / X;
			payments.add(payment);
			if (payment <= M) {
				// check boundary condition
				total = total + M * (maxLoop - i);
				println("===== total (dumb): i: %d, total: %d", i, total);
				return total;
			}
			total += payment;
			balance -= payment;
		}
		if (balance > 0) {
			long subTotal = totalPaid2(balance, X, maxLoop);
			println("use geo equation, total before geo: %d, geo sub total: %d, final total: %d", total, subTotal, total + subTotal);
			total += subTotal;
		}
		return total;
	}

	public static long totalPaid2(long balance, long X, long maxLoop) {
		long lastDay = 1 + (long) (Math.log((M + 0.0) * X / balance) / Math.log(1 - 1.0 / X));
		double rate = 1 - 1.0 / X;
		System.out.println(rate);
		long a1 = balance / X;
		long sum = (long) (a1 * (1 - Math.pow(rate, lastDay)) / (1 - rate));
		long sum2 = sum + (K - lastDay) * M;
		println("totalPaid2: balance: %d, X: %d, sum: %d, sum2: %d", balance, X, sum, sum2);
		return sum2;
	}


	public static long totalPaidOld(long balance, long X) {

		long lastDay = 1 + (long) (Math.log((M + 0.0) * X / balance) / Math.log(1 - 1.0 / X));
		double rate = 1 - 1.0 / X;
		println("rate: %f", rate);
		long a1 = balance / X;
		long sum = (long) (a1 * (1 - Math.pow(rate, lastDay)) / (1 - rate));

		long sum10 = (long) (a1 * (1 - Math.pow(rate, 10)) / (1 - rate));
		long sum10X = totalPaid(balance, X, 10);
		long diff = sum10X - sum10;
		println("diff: %d, sum10: %d, sum10x: %d", diff, sum10, sum10X);

		println("X: %d, sum: %d", X, sum);
		long sum2 = sum + (K - lastDay) * M;
		println("totalPaidOld: balance: %d, X: %d, sum: %d, sum2: %d", balance, X, sum, sum2);
		return sum2;
	}

}
