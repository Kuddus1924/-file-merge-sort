import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;

public class Sorter {
    private String nameOut;
    private ArrayList <String> nameIn;
    private char typeData;
    private char typeSort;

    public Sorter(String out,ArrayList<String> in, char modData, char modSort)
    {
        nameOut = out;
        nameIn = in;
        typeData = modData;
        typeSort = modSort;
    }
    public void sort() throws Exception
    {
        ArrayList tmp;
        if((typeData == 'i' || typeData == 's')) {
            tmp = new ArrayList<String>();
        }
        else
        {
            throw new Exception("Invalid data type");
        }
        if( typeSort != 'a' && typeSort != 'd')
        {
            throw new Exception("Invalid sort type");
        }
        ArrayList<ReaderTXT> sourse = new ArrayList<>();
        for(int i = 0;i < nameIn.size(); i++)
        {
            try {
                sourse.add(new ReaderTXT(nameIn.get(i), typeData));
            }
            catch (FileNotFoundException e)
            {
                System.out.println(e.getMessage());
                continue;
            }
            tmp.add(sourse.get(sourse.size() - 1).getSting(typeSort));
        }
        FileWriter fileWriter = new FileWriter(nameOut);
        while (checkString(tmp))
        {
            int index;
           if(typeData == 'i') {
               index = findNumber(tmp);
               fileWriter.write(tmp.get(index) + "\n");
               fileWriter.flush();
               tmp.set(index,sourse.get(index).getSting(typeSort));
               continue;
            }
            if(typeData == 's')
            {
                index = findString(tmp);
                String t = (String) tmp.get(index);
                fileWriter.write(t + "\n");
                fileWriter.flush();
                tmp.set(index,sourse.get(index).getSting(typeSort));
            }

        }
        fileWriter.close();
    }
    private boolean checkString(ArrayList<String> list)
    {
        for(int i = 0; i < list.size();i++)
        {
            if(list.get(i) != " ")
            {
                return true;
            }
        }
        return false;
    }
    private int findNumber(ArrayList<String> list)
    {
        int index = 0;
        if(typeData == 'i')
        {

            while (list.get(index) == " ")
            {
                index++;
            }
            int tmp = Integer.parseInt(list.get(index));
            for (int i = index;i < list.size(); i++)
            {
                if(list.get(i) == " ") {
                 continue;
                }
                if(typeSort == 'a')
                {
                    if(tmp > Integer.parseInt(list.get(i)))
                    {
                        index = i;
                        tmp = Integer.parseInt(list.get(i));
                    }
                    continue;
                }
                if(typeSort == 'd')
                {
                    if(tmp < Integer.parseInt(list.get(i)))
                    {
                        index = i;
                        tmp = Integer.parseInt(list.get(i));
                    }
                }
            }
        }
        return index;
    }
    private int findString(ArrayList<String> list)
    {
        int index = 0;
        if(typeData == 's') {
            while (list.get(index) == " ") {
                index++;
            }
            String tmp = list.get(index);
            for (int i = index; i < list.size(); i++) {
                if (list.get(i) == " ") {
                    continue;
                }
                if (typeSort == 'a') {
                    if (tmp.compareTo(list.get(i)) > 0) {
                        index = i;
                        tmp = list.get(i);
                    }
                    continue;
                }
                if (typeSort == 'd') {
                    if (tmp.compareTo(list.get(i)) < 0) {
                        index = i;
                        tmp = list.get(i);
                    }
                }
            }
        }
        return index;
    }




}
