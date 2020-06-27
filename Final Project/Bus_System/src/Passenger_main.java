import java.util.Scanner;
import java.util.*;

public class Passenger_main {
	static Passenger[] Passenger = new Passenger[100];
	static int number = 0;
	static Scanner scanner=new Scanner(System.in);
	public static void main(String[] args) {
		int choice;
		System.out.println("This is a bus management system with a maximum staff capacity of 100. You are welcome to use this system");
		 Passenger p = new Passenger();
		 System.out.println(p);
		while(true)
		{
			System.out.println("Please enter what you want to do:");
			System.out.println("1.Observe seats");
			System.out.println("2.Show Seats_Imformation");
			System.out.println("3.Bus_Imformation");
			System.out.println("4.show_bus_imformation");
			System.out.println("5.PayingBus()");
			choice=scanner.nextInt();
			if (0 == choice)
			{
				break;
			}
			switch(choice)
			{
			case 1: Select_Seats();break;
			
			case 2:show_Pass_Select_seats();break;
			
			case 3:Bus_Imformation();break;
			
			case 4:show_bus_imformation();break;
			
			case 5:PayingBus();break;
			
			}
		}
	}
	static void Select_Seats()
	{
		
		while (true)
		{
			System.out.println("What'your name and sex?");
			Passenger[number] = new Passenger();
			Passenger[number].setName(scanner.next());
			Passenger[number].setSex(scanner.next());
			number++;
		   System.out.println("you can select seats_id");
		   System.out.println("id:1 2 3;please select");
		   int id = scanner.nextInt();
		   switch(id)
		   {
		   case 1: Passenger[0].setId(1);break;
		   case 2: Passenger[1].setId(2);break;
		   case 3: Passenger[2].setId(3);break;
		   }
		   System.out.println("Please enter 0 finish");
		   if (scanner.nextInt() == 0)
			   break;
		}
	}
	
	static void Bus_Imformation()
	{
		Passenger[0] = new Passenger();
		System.out.println("There are several bus information below, please choose a bus");
		System.out.println("1. start:惠州 end:广州 name:liziyang id:203");
		System.out.println("2. start:江门 end:广州 name:huangshihao id:204");
		System.out.println("3. start:梅州 end:广州 name:sdas id:205");
		int id_1 = scanner.nextInt();
			if (id_1 == 1)
			{
			Passenger[0].setBus_start("惠州");
			Passenger[0].setBus_end("广州");
			Passenger[0].setDriverName("liziyang");
			Passenger[0].setDriverId(203);
			}
		
		if (id_1 == 2)
		{
			Passenger[0].setBus_start("江门");
			Passenger[0].setBus_end("广州");
			Passenger[0].setDriverName("huangshihao");
			Passenger[0].setDriverId(204);
			
		}
		if (id_1 == 3)
			{Passenger[0].setBus_start("梅州");
			Passenger[0].setBus_end("广州");
			Passenger[0].setDriverName("sdas");
			Passenger[0].setDriverId(205);
			
		}
	}
	
	static void show_Pass_Select_seats() {
		System.out.println("*******************************************");
		System.out.println("name    sex    id");
		for (int i = 0;i < number;i++)
		{
			System.out.println(Passenger[i].getName()+"\t"+Passenger[i]. getSex()+"\t"+Passenger[i].getId());
			System.out.println("*******************************************");
		}
		 System.out.println();
	}
	static void show_bus_imformation()
	{
		System.out.println("*******************************************");
		System.out.println("Start   end   DriverId    DriverName");
		System.out.println(Passenger[0].getBus_start()+"\t"+Passenger[0].getBus_end()+"\t"+Passenger[0].getDriverId()+"\t"+Passenger[0].getDriverName());
	}
	static void PayingBus()
	{
		System.out.println("Please pay for the bus");
		if (Passenger[0].getDriverId() == 203)
		{
			System.out.println("You should pay for 50RMB");
		}
		if (Passenger[0].getDriverId()==204)
		{
			System.out.println("You should pay for 100RMB");
		}
		if (Passenger[0].getDriverId() == 205)
		{
			System.out.println("You should pay for 150RMB");
		}
	}
}
