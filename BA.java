import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
/**@author Enitan Meduteni
 * @course Operating Systems: Banker's Algorithm
 */
public class BA{
    final static int NUMBER_OF_CUSTOMERS = 5;
    final static int NUMBER_OF_RESOURCES = 4;
    //the available instances of each resource
    static int[] available = new int [NUMBER_OF_RESOURCES];
    //the maximum demand of each customer
    static int[][] maximum = new int [NUMBER_OF_CUSTOMERS][NUMBER_OF_RESOURCES];
    //the instances currently allocated to each customer
    static int[][] allocation = new int [NUMBER_OF_CUSTOMERS][NUMBER_OF_RESOURCES];
    //the outstanding need of each customer
    static int[][] need = new int [NUMBER_OF_CUSTOMERS][NUMBER_OF_RESOURCES];

    //method to simulate a process requesting resources. Requests are only granted if the system will remain in a safe state
    public static void requestResources (int customerNumber, int[]available, int[]request){
        if (request[0] <= available[0] && request[1] <= available[1] && request[2] <= available[2] && request[3] <= available[3]){
            allocateResources(customerNumber, request);
            System.out.println("RESOURCES HAVE BEEN SUCCESSFULLY RELEASED");
        }
        else{
            System.out.println("REQUEST DENIED. THIS REQUEST WILL RESULT IN AN UNSAFE STATE");
        }
    }
    
    //method to simulate the successful allocation of resources to a process. This updates the available matrix accordingly.
    public static void allocateResources(int customerNumber, int[] release){
        need[customerNumber][0] = need[customerNumber][0] - release[0];
        available[0] = available[0] - release[0];
        need[customerNumber][1] = need[customerNumber][1] - release[1];
        available[1] = available[1] - release[1];
        need[customerNumber][2] = need[customerNumber][2] - release[2];
        available[2] = available[2] - release[2];
        need[customerNumber][3] = need[customerNumber][3] - release[3];
        available[3] = available[3] - release[3];
    }
    
    //method to simulate the successful release of resources by a process. This updates the available matrix accordingly
    public static void releaseResources(int customerNumber, int[]available, int[] allocate){
        available[0] = available[0] + allocate[0];
        available[1] = available[1] + allocate[1];
        available[2] = available[2] + allocate[2];
        available[3] = available[3] + allocate[3];
    }

    //method to calculate the outstanding need of each customer
    public static void needCalculation(){
        for (int i = 0; i < NUMBER_OF_CUSTOMERS; i ++){
            for (int j = 0; j < NUMBER_OF_RESOURCES; j ++){
                need[i][j] = maximum[i][j] - allocation[i][j];
            }
        }
    }

    public static void main(String[] args) throws Exception {

        //read in the number of instances for each resource
        Scanner availableScanner = new Scanner(new File("available.txt"));
        while (availableScanner.hasNextInt()){
            for (int i = 0; i < available.length; i++){
                available[i] = availableScanner.nextInt();
            }
        }
        availableScanner.close();
        System.out.println("AVAILABLE RESOURCES\n" + Arrays.toString(available));

        //read in the max requests and populate the matrix    
        Scanner requests = new Scanner(new File("maximum.txt"));
        while (requests.hasNextInt()){
            for (int i = 0; i < NUMBER_OF_CUSTOMERS; i++){
                for (int j = 0; j < NUMBER_OF_RESOURCES; j++){
                    maximum[i][j] = requests.nextInt();
                }
            }
        }
        requests.close();
        System.out.println("MAX NUMBER OF REQUESTS FOR EACH CUSTOMER\n" + Arrays.deepToString(maximum));

        //read in the allocation and populate the matrix    
        Scanner allocationScanner = new Scanner(new File("allocation.txt"));
        while (allocationScanner.hasNextInt()){
            for (int i = 0; i < NUMBER_OF_CUSTOMERS; i++){
                for (int j = 0; j < NUMBER_OF_RESOURCES; j++){
                    allocation[i][j] = allocationScanner.nextInt();
                }
            }
        }
        allocationScanner.close();
        System.out.println("RESOURCE ALLOCATION\n" + Arrays.deepToString(allocation));

        //calculate need matrix
        needCalculation();
        System.out.println("NEED MATRIX\n" + Arrays.deepToString(need));
        System.out.println("ENTER A COMMAND:");

        //read user command
        Scanner scanner = new Scanner(System.in);
        String instruction = scanner.next();
        int[] values = new int [4];
        int customerNumber = scanner.nextInt();
        values[0] = scanner.nextInt();
        values[1] = scanner.nextInt();
        values[2] = scanner.nextInt();
        values[3] = scanner.nextInt();  
        //execute function based on user command
        switch(instruction.toUpperCase()) {
            case "DAL":
                releaseResources(customerNumber,available, values);
                break;
            case "RQ":
                requestResources(customerNumber, available, values);
                break;
            default:
                System.err.println("Invalid Command");
                System.exit(0);
            }
        System.out.println("\nRESOURCES CURRENTLY AVAILABLE\n" + Arrays.toString(available));
        System.out.println("\nCURRENT NEED MATRIX\n" + Arrays.deepToString(need));
        //close scanner to avoid resource leaks 
        scanner.close(); 
    }
}