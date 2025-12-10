import java.util.Scanner;

public class Runner
{
    public static void main(String[] args)
    {
        Chatbot maggie = new Chatbot();
        System.out.println (maggie.getGreeting());
        Scanner in = new Scanner (System.in);
        String statement = in.nextLine();

        while (!statement.equals("Bye"))
        {
            System.out.println (maggie.getResponse(statement));
            statement = in.nextLine();
        }
        System.out.println("Thank you for the conversation. Have a nice day.");
        in.close();
    }
}