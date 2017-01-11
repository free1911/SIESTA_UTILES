import java.io.File;
import java.util.Formatter;
import java.util.Scanner;

/**
 * Created by fr on 1/9/2017.
 */
public class combineXYZ {
    public void combine()throws Exception{
        File dir=new File(".\\ANIs");
        File[] subDir=dir.listFiles();
        for(int i=0;i<subDir.length;i++){
            File[] xyz=subDir[i].listFiles();
            System.out.println(subDir[i].getPath()+"\\total.xyz");
            Formatter out=new Formatter(subDir[i].getPath()+"\\total.xyz");
            for(int j=0;j<xyz.length-1;j++){
                System.out.println(subDir[i].getPath()+"\\"+(j+1)+".xyz");
                Scanner in=new Scanner(new File(subDir[i].getPath()+"\\"+(j+1)+".xyz"));
                while(in.hasNext()){
                    out.format("%s\n",in.nextLine());
                }
                in.close();
            }
            out.close();
        }
    }
}
