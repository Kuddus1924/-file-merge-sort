import java.io.*;

public class ReaderTXT {
    private File file;
    private BufferedReader reader;
    public char modT;
    private String prev = null;
    private boolean error = false;

    public ReaderTXT(String name, char data) throws FileNotFoundException
    {
        modT = data;
        file = new File(name);
        reader = new BufferedReader(new FileReader(file));
    }
    public String getSting(char mod)
    {
        if(error)
        {
            return null;
        }
        String result = null;
        try {
            result = reader.readLine();
            if(result == null)
            {
                return " ";
            }
            if(modT == 'i' && !result.matches("[0-9+-]+") )
            {
                error = true;
                return " ";
            }
            if(prev == null)
            {
                prev = result;
            }
            else
            {
                if(mod == 'a') {
                    if(modT == 'i')
                    {
                        if(Integer.parseInt(result) < Integer.parseInt(prev)) {
                            error = true;
                            return " ";
                        }
                    }
                    else {
                        if (prev.compareTo(result) > 0) {
                            error = true;
                            return " ";
                        }
                    }
                }
                if(mod == 'd') {
                    if(modT == 'i')
                    {
                        if(Integer.parseInt(result) > Integer.parseInt(prev)) {
                            error = true;
                            return " ";
                        }
                    }
                    else {
                        if (prev.compareTo(result) < 0) {
                            error = true;
                            return " ";
                        }
                    }
                }
            }
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        prev = result;
        return result;
    }



}
