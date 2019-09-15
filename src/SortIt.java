import java.util.ArrayList;

public class SortIt {
    public static void main(String [] args)
    {
        int index  = 0;
        String outFile;
        char sortType;
        char dataType = args[index].charAt(1);
        index++;
        if(args[index].length() > 2)
        {
            sortType = 'a';
        }
        else
        {
            sortType = args[index].charAt(1);
            index++;
        }
        outFile = args[index];
        index++;
        ArrayList<String> list = new ArrayList<>();
        if(index == args.length)
        {
            System.out.println("No input files entered");
            return;
        }
        for(int i = index; i < args.length; i++)
        {
            list.add(args[i]);
        }
        Sorter sorter = new Sorter(outFile,list,dataType,sortType);
        try {
            sorter.sort();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

}
