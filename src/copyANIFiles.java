import java.io.File;
import java.util.Formatter;
import java.util.Scanner;

public class copyANIFiles {
    private void copyANI(File aniIn,String subname,int name){
        File opDir=new File("ANIs");
        if(!opDir.exists())opDir.mkdir();
        opDir=new File("./ANIs/"+subname);
        if(!opDir.exists())opDir.mkdir();
        try{
            Formatter output=new Formatter("./ANIs/"+subname+"/"+name+".xyz");
            Scanner input=new Scanner(aniIn);
            while (input.hasNext())
                output.format("%s\n",input.nextLine());
            input.close();
            output.close();
        }catch (Exception e){
            System.out.println(e+"\nOperation failed.\n");
            opDir.delete();
        }

    }

    public void copy (String pathname)throws Exception{
        File mainDir=new File(pathname);
        File[] secDir=mainDir.listFiles();
        for(int j=0;j<secDir.length;j++){
            File[] subDir=secDir[j].listFiles();
            int name=0;
            String subname=secDir[j].getName();
            System.out.println(subname);
            for(int i=0;i<subDir.length;i++){
                File file=new File(subDir[i].getAbsolutePath()+"/input.fdf");
                Scanner input=new Scanner(file);
                input.nextLine();
                input.next();
                String aniName=input.next();
                input.close();
                file=new File(subDir[i].getAbsolutePath()+"/"+aniName+".ANI");
                System.out.println(file.getName());
                if(file.exists()){
                    name++;
                    copyANI(file,subname,name);
                }
            }
        }
    }

    private boolean isUnique(File f1,File f2)throws Exception{
        Scanner in1=new Scanner(f1);
        Scanner in2=new Scanner(f2);
        for(int i=0;i<2;i++){
            in1.nextLine();
            in2.nextLine();
        }
        if(in1.nextLine().equals(in2.nextLine())){
            in1.close();
            in2.close();
            System.out.println("XXXXXXXXXX");
            return false;
        }else {
            in1.close();
            in2.close();
            return true;
        }
    }

    public void check()throws Exception{
        File dir=new File(".\\ANIs");
        File[] subDir=dir.listFiles();
        for(int i=0;i<subDir.length;i++){
            File[] xyz=subDir[i].listFiles();
            for(int j=0;j<xyz.length-1;j++){
                    System.out.println(isUnique(xyz[j],xyz[j+1]));
                    if(!isUnique(xyz[j],xyz[j+1]))
                        System.out.println(xyz[j].delete());
            }
        }
    }

    public void refactor(){
        File dir=new File(".\\ANIs");
        File[] subDir=dir.listFiles();
        for(int i=0;i<subDir.length;i++){
            int name=0;
            for(int j=0;j<subDir[i].listFiles().length;j++){
                File xyz=new File(subDir[i].getPath()+"\\"+(j+1)+".xyz");
                if(!xyz.exists())name--;
                name++;
                xyz.renameTo(new File(subDir[i].getPath()+"\\"+(name)+".xyz"));
                System.out.println(xyz.getPath());
            }
        }
    }
}
