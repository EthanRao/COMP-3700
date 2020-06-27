
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
///////////////////////////////Administration's password is 123,thank you


class doModel {
    private static Graph g;
    private static int pointNum = -1;
    private static List<Graph> graphs = new ArrayList<>();
    private static List<stationModel> stationModels = new ArrayList<>();
    private static List<busModel> busModels = new ArrayList<>();
    private static List<routesModel> routesModels = new ArrayList<>();



    private static void changeBus(int type, String id, String name, String direct, int newsite1, int newsite2) {
        String path = "src/data/buses.txt";
        List<String[]> data = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(path));
            System.out.print("");
            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                data.add(str.split(" "));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[][] result = data.toArray(new String[][]{});

        if (type == 1) {
            writeIn(path, id + " " + name + " " + direct + " " + String.valueOf(newsite1) + " " + String.valueOf(newsite2));
        } else if (type == 2) {
            for (int i = 1; i < result.length; i++) {
                if (result[i][0].equals(id)) {
                    autoReplace(path, result[i][1], name);
                }
            }
        } else if (type == 3) {
            for (int i = 1; i < result.length; i++) {
                if (result[i][0].equals(id)) {
                    autoReplace(path, "\r\n" + result[i][0] + " " + result[i][1] + " " + result[i][2] + " " + result[i][3] + " " + result[i][4], "");
                }
            }
        } else {
            System.out.println("error!");
        }

        clear();
        Initial();
    }


    public static void changeStation(int type, String content, String oldStr, String newStr) {

        String path = "src/data/stations.txt";

        List<String[]> stationdata = new ArrayList<>();
        try {
            Scanner scanner2 = new Scanner(new File(path));

            while (scanner2.hasNextLine()) {
                String str2 = scanner2.nextLine();
                stationdata.add(str2.split(" "));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[][] result2 = stationdata.toArray(new String[][]{});

        if (type == 1) {
            writeIn(path, content);

            String num = result2[0][0];
            String num2 = String.valueOf(Integer.parseInt(num) + 1);
            autoReplace(path, num, num2);
            autoReplace(path, num2 + " " + result2[Integer.parseInt(num)][1], num + " " + result2[Integer.parseInt(num)][1]);

        } else if (type == 2) {
            autoReplace(path, oldStr, newStr);
        } else if (type == 3) {


            autoReplace(path, "\r\n" + findStationInStations(oldStr), "");

            String num = result2[0][0];
            String num2 = String.valueOf(Integer.parseInt(num) - 1);
            autoReplace(path, num, num2);
            autoReplace(path, num2 + " " + result2[Integer.parseInt(num)][1], num + " " + result2[Integer.parseInt(num)][1]);
        } else {
            System.out.println("error！");
        }
        clear();
        Initial();

    }


    public static void changeRoutes(int type, String id, int oldPoint1, int oldPoint2, int newPoint1, int newPoint2, int length) {
        String path = "src/data/routes.txt";
        String path2 = "src/data/buses.txt";

        List<String[]> routes = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(path));
            while (sc.hasNextLine()) {
                String str1 = sc.nextLine();

                System.out.print("");
                routes.add(str1.split(" "));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[][] result1 = routes.toArray(new String[][]{});


        List<String[]> busdata = new ArrayList<>();
        try {
            Scanner scanner3 = new Scanner(new File(path2));

            int dsfdsfdfs = 1;
            while (scanner3.hasNextLine()) {
                String str3 = scanner3.nextLine();
                busdata.add(str3.split(" "));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[][] result3 = busdata.toArray(new String[][]{});


        if (type == 1) {
            String olds = null;
            for (int i = 1; i < result1.length; i++) {
                if (result1[i][1].equals(String.valueOf(oldPoint1)) && result1[i][2].equals(String.valueOf(oldPoint2)) && result1[i][0].equals(id)) {
                    olds = result1[i][1] + " " + result1[i][2] + " " + result1[i][3];

                    for (int j = 1; j < result3.length; j++) {
                        if (id.equals(result3[j][0])) {
                            if (String.valueOf(oldPoint1).equals(result3[j][3])) {

                                autoReplace(path2, result3[j][0] + " " + result3[j][1] + " " + result3[j][2] + " " + result3[j][3] + " " + result3[j][4],
                                        result3[j][0] + " " + result3[j][1] + " " + result3[j][2] + " " + String.valueOf(newPoint1) + " " + result3[j][4]);
                            } else if (String.valueOf(oldPoint2).equals(result3[j][4])) {
                                autoReplace(path2, result3[j][0] + " " + result3[j][1] + " " + result3[j][2] + " " + result3[j][3] + " " + result3[j][4],
                                        result3[j][0] + " " + result3[j][1] + " " + result3[j][2] + " " + String.valueOf(newPoint2) + " " + result3[j][4]);
                            }
                        }
                    }
                }
            }
            if (olds == null) {
                System.out.println("error!");
                return;
            }
            autoReplace(path, olds, newPoint1 + " " + newPoint2 + " " + length);
        }

        else if (type == 2) {
            String oldLength = null;

            for (int i = 1; i < result1.length; i++) {
                if (result1[i][1].equals(String.valueOf(oldPoint1)) && result1[i][2].equals(String.valueOf(oldPoint2)) && result1[i][0].equals(id)) {
                    oldLength = result1[i][3];
                }
            }
            if (oldLength == null) {
                System.out.println("error!");
                return;
            }
            String s1 = oldPoint1 + " " + oldPoint2 + " " + oldLength;
            String s2 = oldPoint1 + " " + oldPoint2 + " " + length;
            autoReplace(path, s1, s2);
        }

        else if (type == 3) {
            boolean flag = false;

            String firstpoint1 = null;
            String sepoint2 = null;
            int pointcount = 0;
            for (int i = 1; i < result1.length; i++) {

                if ((result1[i][2].equals(String.valueOf(oldPoint1)) || result1[i][1].equals(String.valueOf(oldPoint1))) && result1[i][0].equals(id)) {
                    flag = true;
                    if (result1[i][2].equals(String.valueOf(oldPoint1))) {
                        firstpoint1 = result1[i][1];
                        pointcount++;
                    }
                    if (result1[i][1].equals(String.valueOf(oldPoint1))) {
                        sepoint2 = result1[i][2];
                        pointcount++;
                    }
                    if (pointcount != 2) {
                        String s1 = result1[i][0] + " " + result1[i][1] + " " + result1[i][2] + " " + result1[i][3];
                        autoReplace(path, "\r\n" + s1, "");
                    } else {

                        String s1 = result1[i][0] + " " + result1[i][1] + " " + result1[i][2] + " " + result1[i][3];

                        String s2 = result1[i][0] + " " + firstpoint1 + " " + sepoint2 + " " + result1[i][3];
                        autoReplace(path, s1, s2);
                    }



                    for (int j = 1; j < result3.length; j++) {
                        if (result3[j][0].equals(id)) {

                            if (String.valueOf(oldPoint1).equals(result3[j][3])) {

                                autoReplace(path2, result3[j][0] + " " + result3[j][1] + " " + result3[j][2] + " " + result3[j][3] + " " + result3[j][4],
                                        result3[j][0] + " " + result3[j][1] + " " + result3[j][2] + " " + result1[i][2] + " " + result3[j][4]);
                            } else if (String.valueOf(oldPoint1).equals(result3[j][4])) {
                                autoReplace(path2, result3[j][0] + " " + result3[j][1] + " " + result3[j][2] + " " + result3[j][3] + " " + result3[j][4],
                                        result3[j][0] + " " + result3[j][1] + " " + result3[j][2] + " " + result1[i][1] + " " + result3[j][4]);
                            }
                        }
                    }

                }
            }
            if (!flag) {
                System.out.println("error!");
            }
        }

        else if (type == 4) {
            writeIn(path, id + " " + String.valueOf(newPoint1) + " " + String.valueOf(newPoint2) + " " + String.valueOf(length));
        } else {
            System.out.println("error!");
        }
        clear();
        Initial();
    }



    private static void writeIn(String file, String content) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file, true)));
            out.write("\r\n" + content);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    private static void autoReplace(String filePath, String oldstr, String newStr) {
        File file = new File(filePath);
        Long fileLength = file.length();
        byte[] fileContext = new byte[fileLength.intValue()];
        FileInputStream in = null;
        PrintWriter out = null;
        try {
            in = new FileInputStream(filePath);
            in.read(fileContext);

            String str = new String(fileContext, "utf-8");
            str = str.replace(oldstr, newStr);
            out = new PrintWriter(filePath);
            out.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.flush();
                out.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void findIdInRouteANDPrint(String s) {
        for (int i = 0; i < routesModels.size(); i++) {
            if (routesModels.get(i).getRouteId().equals(s)) {
                for (int k = 0; k < routesModels.get(i).Site.size(); k++) {
                    if (k == 0) {
                        System.out.print(routesModels.get(i).Site.get(k));
                    } else {
                        System.out.print(" --> " + routesModels.get(i).Site.get(k));
                    }
                }
                System.out.println();
            }
        }
    }


    public static void findSiteInRouteANDPrint(String s) {
        for (int i = 0; i < routesModels.size(); i++) {
            for (int j = 0; j < routesModels.get(i).Site.size(); j++) {
                if (routesModels.get(i).Site.get(j).equals(s)) {

                    for (int k = 0; k < routesModels.get(i).Site.size(); k++) {
                        if (k == 0) {
                            System.out.print(routesModels.get(i).Site.get(k));
                        } else {
                            System.out.print(" -> " + routesModels.get(i).Site.get(k));
                        }
                    }
                    System.out.println();
                    break;
                }
            }
        }
    }


    private static String findStationInStations(String station) {
        for (int i = 0; i < stationModels.size(); i++) {
            if (station.equals(stationModels.get(i).getName())) {
                String ans = stationModels.get(i).getId() + " " + station;
                return ans;
            }
        }
        return null;
    }


    public static void findPathInGraph(int type, int start, int end) {

        int temp[];
        if (type == -1) {
            temp = g.findPath(start, end);
        } else {
            temp = graphs.get(type).findPath(start, end);
        }
        if (temp != null) {
            for (int i = 1; i < temp.length; i++) {
                if (temp[i] != -1)
                    System.out.print("site" + (temp[i] + 1) + " ->");
            }
            System.out.println("Destination");

            System.out.println("length：" + temp[0]);
        } else {
            System.out.println("no find");
        }
    }


    private static boolean isHasPath(int start, int end) {
        return g.findPath(start, end) != null;
//        if (g.findPath(start, end) != null) {
//            return true;
//        }
//        return false;
    }

    public static int[] isHaveBuses(int start, int end) {
        int temp[] = new int[2];
        temp[0] = -1;
        temp[1] = -1;
        if (isHasPath(start, end)) {

            for (int i = 0; i < routesModels.size(); i++) {
                if (routesModels.get(i).isHasPath(start, end)) {
                    temp[0] = i + 1;
                    return temp;
                }
            }

            int[] route1 = new int[routesModels.size()];
            int[] route2 = new int[routesModels.size()];
            for (int i = 0; i < routesModels.size(); i++) {
                route1[i] = -1;
                route2[i] = -1;
            }
            int tempRoute1 = 0;
            int tempRoute2 = 0;

            for (int i = 0; i < routesModels.size(); i++) {
                int temp1 = routesModels.get(i).Site.indexOf(String.valueOf(start));
                int temp2 = routesModels.get(i).Site.indexOf(String.valueOf(end));
                if (temp1 != -1) {
                    route1[tempRoute1++] = i;
                }
                if (temp2 != -1) {
                    route2[tempRoute2++] = i;
                }
            }


            for (int i = 0; i < routesModels.size(); i++) {
                for (int j = 0; j < routesModels.size(); j++) {
                    if (route1[i] != -1 && route2[j] != -1 && route1[i] != route2[j]) {
                        for (int m = 0; m < routesModels.get(route1[i]).Site.size(); m++) {
                            for (int n = 0; n < routesModels.get(route2[j]).Site.size(); n++) {
                                if (routesModels.get(route1[i]).Site.get(m).equals(routesModels.get(route2[j]).Site.get(n))
                                        && m > routesModels.get(route1[i]).Site.indexOf(String.valueOf(start))
                                        && n < routesModels.get(route2[j]).Site.indexOf(String.valueOf(end))) {
                                    temp[0] = route1[i] + 1;
                                    temp[1] = route2[j] + 1;
                                    return temp;
                                }
                            }
                        }
                    }
                }
            }
        } else {
            return null;
        }
        return null;
    }



    private static int findIdInGraphReturnIndex(String s) {
        for (int j = 0; j < graphs.size(); j++) {

            if (graphs.get(j).getId().equals(s)) {
                return j;
            }
        }
        return -1;
    }


    private static int findIdInRouteReturnIndex(String s) {
        for (int j = 0; j < routesModels.size(); j++) {

            if (routesModels.get(j).getRouteId().equals(s)) {
                return j;
            }
        }
        return -1;
    }

    public static void showRoutes() {
        for (int i = 0; i < routesModels.size(); i++) {
            routesModels.get(i).show();
        }
    }

    public static void showGraphs() {
        for (int i = 0; i < graphs.size(); i++) {
            graphs.get(i).show();
            System.out.println("_________________________________________");
        }
    }

    public static void showStation() {
        for (int i = 0; i < stationModels.size(); i++) {
            stationModels.get(i).show();
        }
    }

    public static void showBus() {
        for (int i = 0; i < busModels.size(); i++) {
            busModels.get(i).show();
        }
    }


    private static void InitialStation() {

        List<String[]> stationdata = new ArrayList<>();
        try {
            Scanner scanner2 = new Scanner(new File("src/data/stations.txt"));

            while (scanner2.hasNextLine()) {
                String str2 = scanner2.nextLine();
                stationdata.add(str2.split(" "));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[][] result2 = stationdata.toArray(new String[][]{});

        g = new Graph(Integer.parseInt(result2[0][0]));
        pointNum = Integer.parseInt(result2[0][0]);
        int x2 = result2.length;
//        int y2 = result2[1].length;


        for (int i = 1; i < x2; i++) {
            stationModel stationModel = new stationModel();
            stationModels.add(stationModel.add(result2[i][0], result2[i][1]));
        }

    }


    private static void InitialBus() {
        List<String[]> busdata = new ArrayList<>();
        try {
            Scanner scanner3 = new Scanner(new File("src/data/buses.txt"));

            while (scanner3.hasNextLine()) {
                String str3 = scanner3.nextLine();
                busdata.add(str3.split(" "));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        String[][] result3 = busdata.toArray(new String[][]{});

        int x3 = result3.length;
        for (int i = 1; i < x3; i++) {
            busModel bus = new busModel();
            busModels.add(bus.add(result3[i][0], result3[i][1], result3[i][2], result3[i][3], result3[i][4]));
        }
    }


    private static void InitialRoutesANDGraphs() {

        List<String[]> routedata = new ArrayList<>();
        try {
            Scanner scanner1 = new Scanner(new File("src/data/routes.txt"));

            while (scanner1.hasNextLine()) {
                String str1 = scanner1.nextLine();

                routedata.add(str1.split(" "));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        String[][] result1 = routedata.toArray(new String[][]{});

        int x1 = result1.length;
        int y1 = result1[1].length;
//        System.out.println(y1);



        for (int i = 1; i < x1; i++) {
            int place = findIdInGraphReturnIndex(result1[i][0]);
            int place1 = findIdInRouteReturnIndex(result1[i][0]);
            if (place1 != -1) {

                routesModels.get(place1).Site.add(result1[i][2]);
                routesModels.get(place1).incDis(Integer.parseInt(result1[i][3]));
            } else {
                routesModel route = new routesModel();
                route.setRouteId(result1[i][0]);
                route.Site.add(result1[i][1]);
                route.Site.add(result1[i][2]);
                route.incDis(Integer.parseInt(result1[i][3]));
                routesModels.add(route);
            }
            if (place != -1) {
                graphs.get(place).add(Integer.parseInt(result1[i][1]), Integer.parseInt(result1[i][2]), Integer.parseInt(result1[i][3]));
                graphs.get(place).incSum(Integer.parseInt(result1[i][3]));
            } else {
//                System.out.println(pointNum);
                Graph graph = new Graph(pointNum);
                graph.setId(result1[i][0]);
                graph.incSum(Integer.parseInt(result1[i][3]));
                graph.add(Integer.parseInt(result1[i][1]), Integer.parseInt(result1[i][2]), Integer.parseInt(result1[i][3]));
                graphs.add(graph);
            }
        }


        for (int i = 1; i < x1; i++) {
            g.setId("Sum");
            g.add(Integer.parseInt(result1[i][1]), Integer.parseInt(result1[i][2]), 0);
        }
    }

    private static void clear() {
        stationModels.clear();
        routesModels.clear();
        busModels.clear();
        graphs.clear();
        g.clear();
    }

    private static void Initial() {
        //clear
        InitialStation();
        InitialBus();
        InitialRoutesANDGraphs();
    }


    private static void System_menu()
    {
        System.out.println("Please enter your choice：");
        System.out.println("1.Passenger");
        System.out.println("2.Admisitration");
        System.out.println("3.Host");
        System.out.println("4.Driver");
        System.out.println("5.Exit!");
    }


    private static void CommonMenu() {
        System.out.println("Please enter your choice：");
        System.out.println("1.--- Query Bus Stop ----");
        System.out.println("2.--- View bus route ----");
        System.out.println("3.--- Reserve a seat ----");
        System.out.println("4.----Look reservation----");
        System.out.println("5.-------navigation--------");
        System.out.println("6.-------exit--------");
    }


    private static void masterMenu() {
        System.out.println("Please select the type you want to operate：");
        System.out.println("1.----Platform information----");
        System.out.println("2.----Route information----");
        System.out.println("3.----Bus information----");
        System.out.println("4.------exit------");
    }


    public void Select_character()
    {
        int choice = -1;
        Initial();
        while (choice != 5)
        {
            System_menu();
            Scanner n = new Scanner(System.in);
            choice = n.nextInt();
            if (choice == 1)
            {
                CommonView();
            }
            if (choice == 2)
            {
                String masterCode = "123";
                System.out.println("Please enter the administrator password：");
                Scanner sc = new Scanner(System.in);
                String code = sc.next();
                if (code.equals(masterCode)){
                    System.out.println("----Welcome----");
                    System.out.println();
                    System.out.println();
                    MasterView();
                }
                else {
                    System.out.println();
                    System.out.println("Incorrect password, will return to the main menu");
                    System.out.println();
                    System.out.println();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (choice == 3)
            {
                HostView();
            }
            if (choice == 4)
            {
                DriverView();
            }
            if (choice == 5)
            {
                System.exit(0);
            }
        }
    }


    public void CommonView() {
        int choose = -1;
        int id = -1;
        Initial();
        while (choose != 5) {
            CommonMenu();
            Scanner ch = new Scanner(System.in);
            choose = ch.nextInt();

            if (choose == 1) {
                System.out.println("Please enter the query site name：");
                Scanner sc1 = new Scanner(System.in);
                String station = sc1.next();
                String ans = findStationInStations(station);
                if (ans == null) {
                    System.out.println("The input is incorrect and will return to the main menu");
                    System.out.println();
                    System.out.println();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }
                String[] s = ans.split(" ");
                System.out.println("The query result is:");
                System.out.println("site's name：" + s[1] + "    number为:" + s[0]);
            } else if (choose == 2) {
                showRoutes();
            } else if (choose == 5) {
                System.out.println("Please enter the starting point number");
                Scanner instart = new Scanner(System.in);
                int start = instart.nextInt();
                System.out.println("Please enter the destination number");
                Scanner inend = new Scanner(System.in);
                int end = inend.nextInt();
                int temp[];
                temp = isHaveBuses(start, end);
                if (temp == null) {
                    System.out.println("The route does not exist!");
                } else if (temp[1] == -1) {
                    System.out.println("The bus line is：" + temp[0] + "Number line");
                } else {
                    System.out.println("The bus line is：" + temp[0] + "Number line" + "change" + temp[1] + "Number line");
                }
                System.out.println();
                System.out.println();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }  else if (choose == 6) {
                System.out.println("will exit...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            else if (choose == 3)
            {
                System.out.println("Below are the seat id numbers of 1,2,3...45,please choose");
                Scanner s = new Scanner(System.in);
                id = s.nextInt();
                if (id >=1 && id <= 45)
                {
                    System.out.println("Seat_id is："+id +" add success!");
                }
                else
                {
                    System.out.println("re-select!");
                }
            }
            else if (choose == 4)
            {
                System.out.println("Your seat id is "+id+" please seat by number");
            }
            else {
                System.out.println("Input error, please reselect");
                System.out.println();
                System.out.println();
                System.out.println();
            }
        }


    }

    public void HostView()
    {
        clear();
        Initial();
        System.out.println("Your task: ");
        System.out.println("Task1:huizhou-guangzhou");
        System.out.println("Task2:huizhou-meizhou");
        System.out.println("Task3:jiangmen-huizhou");
        System.out.println("Please choose: ");
        Scanner s = new Scanner(System.in);
        int taskNum = s.nextInt();
        if (taskNum == 1)
        {
            System.out.println("You will accept huizhou-guangzhou ");
        }
        if (taskNum == 2)
        {
            System.out.println("You will accept huizhou-meizhou ");
        }
        if (taskNum == 3)
        {
            System.out.println("You will accept jiangmen-huizhou ");
        }

    }

    public void DriverView()
    {
        clear();
        Initial();
        System.out.println("Your task: ");
        System.out.println("Task1:huizhou-guangzhou");
        System.out.println("Task2:huizhou-meizhou");
        System.out.println("Task3:jiangmen-huizhou");
        System.out.println("Please choose: ");
        Scanner s = new Scanner(System.in);
        int taskNum = s.nextInt();
        if (taskNum == 1)
        {
            System.out.println("You will accept Huizhou-guangzhou ");
        }
        if (taskNum == 2)
        {
            System.out.println("You will accept huizhou-meizhou ");
        }
        if (taskNum == 3)
        {
            System.out.println("You will accept huizhou-jiangmen ");
        }
    }


    public void MasterView() {
        clear();
        Initial();
        int choose = -1;
        while (choose != 4) {
            masterMenu();
            Scanner ch = new Scanner(System.in);
            choose = ch.nextInt();
            if (choose == 1) {
                System.out.println("1.------write site------");
                System.out.println("2.----Modify station name----");
                System.out.println("3.----Delete station data----");
                System.out.println("4.----Check the platform situation----");
                System.out.println("Please enter your choice");
                Scanner ch1 = new Scanner(System.in);
                int choose1 = ch1.nextInt();
                if (choose1 == 1) {
                    System.out.println("Please enter the site number：");
                    Scanner inId = new Scanner(System.in);
                    String id = inId.next();
                    System.out.println("Please enter the site name：");
                    Scanner inName = new Scanner(System.in);
                    String name = inName.next();
                    changeStation(choose1, id + " " + name, "", "");
                } else if (choose1 == 2) {
                    System.out.println("Please enter the name of the site you want to change:");
                    Scanner inOldName = new Scanner(System.in);
                    String oldName = inOldName.next();
                    System.out.println("Please enter the new site name:");
                    Scanner inNewName = new Scanner(System.in);
                    String newName = inNewName.next();
                    changeStation(choose1, "", oldName, newName);
                } else if (choose1 == 3) {
                    System.out.println("Please enter the name of the site to be deleted:");
                    Scanner inOldName = new Scanner(System.in);
                    String oldName = inOldName.next();
                    changeStation(choose1, "", oldName, "");
                } else if (choose1 == 4) {
                    showStation();
                } else {
                    System.out.println("input error");
                    System.out.println();
                    System.out.println();
                    System.out.println();
                }
            } else if (choose == 2) {
                System.out.println ("1 .--------- Add route ---------");
                System.out.println ("2 .------ Modify the route platform ------");
                System.out.println ("3 .--- Delete the line containing a site ---");
                System.out.println ("4 .--------- modify length ---------");
                System.out.println ("5.-Query all lines passing through a site-");
                System.out.println ("6 .--------- Query line ---------");
                System.out.println ("7 .------- Check the platform situation -------");
                System.out.println ("Please enter your choice");
                Scanner ch2 = new Scanner(System.in);
                int choose2 = ch2.nextInt();

                if (choose2 == 1) {
                    System.out.println ("Please enter the ID number of the route you want to add");
                    Scanner inId = new Scanner (System.in);
                    String id = inId.next ();
                    System.out.println ("Please enter the starting position number:");
                    Scanner inNewPoint1 = new Scanner (System.in);
                    int newPoint1 = inNewPoint1.nextInt ();
                    System.out.println ("Please enter the end position number:");
                    Scanner inNewPoint2 = new Scanner (System.in);
                    int newPoint2 = inNewPoint2.nextInt ();
                    System.out.println ("Please enter the distance between two points:");
                    Scanner inLength = new Scanner (System.in);
                    int length = inLength.nextInt ();
                    changeRoutes (4, id, -1, -1, newPoint1, newPoint2, length);
                } else if (choose2 == 2) {
                    System.out.println ("Please enter the ID number of the route you want to modify");
                    Scanner inId = new Scanner (System.in);
                    String id = inId.next ();

                    System.out.println ("Please enter the starting position number to be modified:");
                    Scanner inOldPoint1 = new Scanner (System.in);

                    int oldPoint1 = inOldPoint1.nextInt ();

                    System.out.println ("Please enter the end position number to be modified:");

                    Scanner inOldPoint2 = new Scanner (System.in);

                    int oldPoint2 = inOldPoint2.nextInt ();

                    System.out.println ("Please enter the new starting position number:");
                    Scanner inNewPoint1 = new Scanner (System.in);
                    int newPoint1 = inNewPoint1.nextInt ();
                    System.out.println ("Please enter the new end position number:");
                    Scanner inNewPoint2 = new Scanner (System.in);
                    int newPoint2 = inNewPoint2.nextInt ();
                    changeRoutes (1, id, oldPoint1, oldPoint2, newPoint1, newPoint2, -1);
                } else if (choose2 == 3) {
                    System.out.println ("Please enter the ID number of the route you want to modify");
                    Scanner inId = new Scanner (System.in);
                    String id = inId.next ();
                    System.out.println ("Please enter the position number to delete:");
                    Scanner inOldPoint1 = new Scanner (System.in);
                    int oldPoint1 = inOldPoint1.nextInt ();
                    changeRoutes (3, id, oldPoint1, -1, -1, -1, -1);
                } else if (choose2 == 4) {
                    System.out.println ("Please enter the ID number of the route you want to change");
                    Scanner inId = new Scanner (System.in);
                    String id = inId.next ();
                    System.out.println ("Please enter the starting position number:");
                    Scanner inOldPoint1 = new Scanner (System.in);

                    System.out.print("");

                    int oldPoint1 = inOldPoint1.nextInt ();
                    System.out.println ("Please enter the end position number:");
                    Scanner inOldPoint2 = new Scanner (System.in);
                    int oldPoint2 = inOldPoint2.nextInt ();

                    System.out.println ("Please enter the distance between the new two points:");
                    Scanner inLength = new Scanner (System.in);
                    int length = inLength.nextInt ();
                    changeRoutes (2, id, oldPoint1, oldPoint2, -1, -1, length);
                } else if (choose2 == 5) {
                    System.out.println ("Please enter the site number to be queried");
                    Scanner sc = new Scanner (System.in);
                    String site = sc.next ();
                    findSiteInRouteANDPrint (site);
                } else if (choose2 == 6) {
                    System.out.println ("Please enter the route number to be queried");
                    Scanner sc = new Scanner (System.in);
                    String id = sc.next ();
                    findIdInRouteANDPrint (id);
                } else if (choose2 == 7) {
                    showRoutes ();
                } else {
                    System.out.println ("Input error");
                    System.out.println ();
                    System.out.println ();
                    System.out.println ();
                }
            } else if (choose == 3) {

                System.out.println("1.------Add entry------");
                System.out.println("2.---Edit bus route number---");
                System.out.println("3.----Delete transit data----");
                System.out.println("4.----View bus status----");
                System.out.println("Please enter your choice");
                Scanner ch3 = new Scanner(System.in);
                int choose3 = ch3.nextInt();
                if (choose3 == 1) {
                    System.out.println("Please enter the number");
                    Scanner inId = new Scanner(System.in);
                    String id = inId.next();
                    System.out.println("Please enter the route number");
                    Scanner inName = new Scanner(System.in);
                    String name = inName.next();
                    System.out.println("Please enter the direction of travel");
                    Scanner inDir = new Scanner(System.in);
                    String Dir = inDir.next();
                    System.out.println("Please enter the starting point number");
                    Scanner inSite1 = new Scanner(System.in);
                    int site1 = inSite1.nextInt();
                    System.out.println("Please enter the destination number");
                    Scanner inSite2 = new Scanner(System.in);
                    int site2 = inSite2.nextInt();
                    changeBus(1, id, name, Dir, site1, site2);
                } else if (choose3 == 2) {
                    System.out.println("Please enter the number");
                    Scanner inId = new Scanner(System.in);
                    String id = inId.next();
                    System.out.println("Please enter a new route number");

                    Scanner inName = new Scanner(System.in);
                    String name = inName.next();
                    changeBus(2, id, name, null, -1, -1);

                } else if (choose3 == 3) {
                    System.out.println("Please enter the number to delete");
                    Scanner inId = new Scanner(System.in);
                    String id = inId.next();
                    changeBus(3, id, null, null, -1, -1);
                } else if (choose3 == 4) {
                    showBus();
                } else {
                }
            } else if (choose == 4) {
                System.out.println("will exit...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Input error, please reselect");
                System.out.println();
                System.out.println();
                System.out.println();
            }
        }
    }
}


class busModel {

    private String id;

    private String name;

    private String destin;

    private String startId;

    private String destinationId;


    public void show() {
        System.out.println ("Line number:" + id + "" + "Line name:" + name + "" + "Direction of travel:" + destin + "" + "Start station number:" + startId + " "+" Terminal number: "+ destinationId);
    }

    public busModel add(String id, String name, String destin, String startId, String destinationId) {
        this.id = id;
        this.name = name;
        this.destin = destin;
        this.startId = startId;
        this.destinationId = destinationId;
        return this;
    }



    public String getDestin() {
        return destin;
    }

    public void setDestin(String destin) {
        this.destin = destin;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartId() {
        return startId;
    }

    public void setStartId(String startId) {
        this.startId = startId;
    }

    public String getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
    }
}

class Graph {

    private int pointNum;
    private String[] point;
    public int graph[][];
    private String id;
    private int sum = 0;



    public Graph(int pointNum) {
        this.pointNum = pointNum;
        point = new String[pointNum];
        graph = new int[pointNum][pointNum];

        for (int i = 0; i < pointNum; i++) {
            for (int j = 0; j < pointNum; j++) {
                graph[i][j] = -1;
            }
        }
    }


    public void add(int x, int y, int num) {
        if (graph[x - 1][y - 1] == -1) {
            graph[x - 1][y - 1] = num;
//            graph[y-1][x-1] = num;
        }
    }


    public void clear(){
        if (graph!=null){
            for (int i = 0; i < pointNum; i++) {
                for (int j = 0; j < pointNum; j++) {
                    graph[i][j] = -1;
                }
            }
        }
        if (point!=null){
            for (int i = 0;i<point.length;i++){
                point[i] = null;
            }
        }
    }


    public void show() {
        for (int i = 0; i < pointNum; i++) {
            for (int j = 0; j < pointNum; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
        for (int i = 0; i < pointNum; i++) {
            point[i] = null;
        }
    }



    public int[] findPath(int start, int end) {
        int path[] = new int[pointNum + 1];
        int front[] = new int[pointNum];
        int dis[] = new int[pointNum];
        boolean isUsed[] = new boolean[pointNum];

        for (int i = 0; i < pointNum; i++) {
            path[i] = -1;
            front[i] = -1;
            dis[i] = -1;
            isUsed[i] = false;
        }

        int startx = start - 1;
        for (int i = 0; i < pointNum; i++) {
            if (graph[startx][i] != -1) {
                isUsed[startx] = true;
                front[i] = startx;
                dis[i] = graph[startx][i];
            }
        }


        for (int num = 1; num < pointNum; num++) {


            if (dis[end - 1] != -1) {

                int endx = end - 1;
                for (int k = 0; k < pointNum; k++) {
                    if (endx != -1) {
                        path[pointNum - k] = front[endx];
                        endx = front[endx];
                    }
                }
                path[0] = dis[end - 1];
                return path;
            }

            int min = 100000000;
            for (int j = 0; j < pointNum; j++) {
                if (!isUsed[j] && dis[j] <= min && dis[j] != -1) {
                    min = dis[j];
                    startx = j;
                }
            }

            isUsed[startx] = true;


            for (int i = 0; i < pointNum; i++) {

                if (graph[startx][i] != -1) {

                    if ((graph[startx][i] + dis[startx]) <= dis[i] || dis[i] == -1) {
                        front[i] = startx;
                        dis[i] = graph[startx][i] + dis[startx];
                    }
                }
            }

//            System.out.println("dis:");
//            for (int s = 0; s < pointNum; s++) {
//                System.out.print(dis[s] + " ");
//            }
//            System.out.println();
//            System.out.println("path:");
//            for (int s = 0; s < pointNum; s++) {
//                System.out.print(front[s] + " ");
//            }
//            System.out.println();
        }
        return null;
    }


    public void incSum(int num) {
        this.sum += num;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPointNum() {
        return pointNum;
    }

    public void setPointNum(int pointNum) {
        this.pointNum = pointNum;
    }

    public String[] getPoint() {
        return point;
    }

    public void setPoint(String[] point) {
        this.point = point;
    }

    public int[][] getGraph() {
        return graph;
    }

    public void setGraph(int[][] graph) {
        this.graph = graph;
    }

}

class stationModel {

    private String id;

    private String name;


    public stationModel add(String id,String name){
        this.id = id;
        this.name = name;
        return this;
    }

    public void show(){
        System.out.println ("Site ID:" + id + "Site Name:" + name);
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class routesModel {


    private String routeId;

    public List<String> Site = new ArrayList<>();

    private int distance;

//    private List<String> siteName = new ArrayList<>();

    public boolean isHasPath(int start,int end){
        String s1 = String.valueOf(start);
        String s2 = String.valueOf(end);
        for (int i = 0;i<Site.size();i++){
            if (Site.indexOf(s1)<Site.indexOf(s2)&&Site.indexOf(s1)!=-1&&Site.indexOf(s2)!=-1){
                return true;
            }
        }
        return false;
    }

    public void show(){
        System.out.println();
        System.out.println();
        System.out.println ("Line ID:" + routeId);
        System.out.println ("The site number path is:");
        for (int i = 0;i<Site.size();i++){
            if (i!=Site.size()-1){
                System.out.print(Site.get(i)+"-->");
            }
            else {
                System.out.print(Site.get(i));
            }
        }
        System.out.println();
    }
    public void incDis(int num){
        distance += num;
    }
    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }


    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}




public class Main {
    private static doModel dom = new doModel();
    public static void main(String[] args) {
        dom.Select_character();
    }


}


