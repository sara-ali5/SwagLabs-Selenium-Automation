package Utilities;

public class CMDRunner {
    public static int executeCMD(String command){

        int exitcode = -1;
        try{

            Process process = Runtime.getRuntime().exec(command);
            exitcode = process.waitFor();
            if(exitcode !=0)
            {
                System.out.println(command +"exited with code" + exitcode);
            }
            else {
                System.out.println("command executed successfully");
            }
        }

        catch (Exception e) {
            e.getStackTrace();

        }

        return exitcode;


    }

}
