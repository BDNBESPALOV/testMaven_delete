package main;

import org.slf4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ImportRepo {
    private static ArrayList<String> arrayListsReport = new ArrayList<>();
    private static ArrayList<String> arrayListsReports = new ArrayList<>();
    private static ArrayList<String> arrayListsLib = new ArrayList<>();
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ImportRepo.class);


   public void startImportFile()  {
         //заполнение ArrayList
         readMyResources();

        String folderFileRepo = Controller3.FILES.replaceAll(".zip(.*)","");
        String report = "/"+folderFileRepo+"/module/bft.gz/report/";
        String reports = "/"+folderFileRepo+"/module/bft.gz/reports/";

          //server SP
        if(!new File(report).isDirectory() || !new File(reports).isDirectory()){
            new File(report).mkdirs();
            new File(reports).mkdirs();
        }
        log.info("report = "+report);
        log.info("report = "+reports);

        new  ImportRepo().insertFile("/module/bft.gz/report/",report,arrayListsReport);
        new  ImportRepo().insertFile("/module/bft.gz/reports/",reports,arrayListsReports);


////        //server tomcat
        String reportTom = "/"+folderFileRepo+"/apache-tomcat-6.0.29_BFT-1.0/webapps/azk/WEB-INF/module/bft.gz/report/";
        String reportsTom = "/"+folderFileRepo+"/apache-tomcat-6.0.29_BFT-1.0/webapps/azk/WEB-INF/module/bft.gz/reports/";
        if(!new File(reportTom).isDirectory() || !new File(reportsTom).isDirectory()){
            new File(reportTom).mkdirs();
            new File(reportsTom).mkdirs();
        }
        new  ImportRepo().insertFile("/module/bft.gz/report/",reportTom,arrayListsReport);
        new  ImportRepo().insertFile("/module/bft.gz/reports/",reportsTom,arrayListsReports);


        //        //bpl client
        String libClient =  folderFileRepo+"/client/";
        log.info(libClient);

        if(!new File(libClient).isDirectory() ){
            new File(libClient).mkdirs();
        }
        new  ImportRepo().insertFile("/lib/",libClient, arrayListsLib);

    }
    private static void insLib(String st){

            try {
                arrayListsLib.add(st.replaceAll("lib/", "") + "");
            }catch (Exception e){
                log.error(e.fillInStackTrace()+"");
            }

    }
    private static void insReport(String s) {
        try {
            arrayListsReport.add(s.replaceAll("module/bft.gz/report/", "") + "");
        }catch (Exception e){
            log.error(e.fillInStackTrace()+"");
        }
    }
    private static void insReports(String s) {
        try {
            arrayListsReports.add(s.replaceAll("module/bft.gz/reports/", "") + "");
        }catch (Exception e){
            log.error(e.fillInStackTrace()+"");
        }
    }


    public void insertFile(String path, String newPath, ArrayList arrayList) {
        log.info("insertFile "+path+"  "+newPath);
       // ArrayList arrayList = arrayLists;/*null;*/
      //  arrayList = arrayInputSt(ImportRepo.class.getResourceAsStream(path));
        log.info("insertFile arrayList "+arrayList+"size()  "+arrayList.size());
        for(int i=1;i< arrayList.size();i++){
            copyFileUsingStreams(ImportRepo.class.getResourceAsStream(
                    path+arrayList.get(i)),
                    new File(newPath+arrayList.get(i)));
        }

    }

    private void copyFileUsingStreams(InputStream is, File dest) {
        log.info("copyFileUsingStreams" + is+"  "+dest);
        OutputStream os = null;
        try {
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } catch (IOException e) {
            log.error(String.valueOf(e));
            e.printStackTrace();
        } finally {
            try {
                is.close();
               os.close();
            } catch (IOException e) {
                log.error(String.valueOf(e));
                e.printStackTrace();
            }

        }

    }

    public ArrayList<String> arrayInputSt (InputStream in)  {
        log.info("arrayInputSt "+in);
        ArrayList<String> arrayList = new ArrayList<>();
        String str2="";
        String str3="";
        Reader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader( in, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error(String.valueOf(e));
            e.printStackTrace();
        }
        int data = 0;
        try {
            data = inputStreamReader.read();
        } catch (IOException e) {
            log.error(String.valueOf(e));
            e.printStackTrace();
        }
        log.info("inputStreamReader "+inputStreamReader);
        log.info("data "+data);
        while(data != -1){
            log.info("str3 "+str3);
            char theChar = (char) data;
            try {
                data = inputStreamReader.read();
            } catch (IOException e) {
                log.error(String.valueOf(e));
                e.printStackTrace();
            }
            if(!"\n".equals(""+theChar)){
                str3+=theChar;
            }
            if ("\n".equals(""+theChar )){
                arrayList.add(str3);
                str2="";
                str3="";
            }
            str2+=theChar;
        }
        try {
            in.close();
            inputStreamReader.close();
        } catch (IOException e) {
            log.error(String.valueOf(e));
            e.printStackTrace();
        }

        return arrayList;
    }

    private void readMyResources() {
        try {
            JarFile jar = new JarFile(new File(getClass().getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .getPath()));
            jar.stream().map(JarEntry::getName).filter(i -> i.startsWith("lib/")).forEach(ImportRepo::insLib);
        }catch (IOException e) {
            log.error(e.fillInStackTrace()+"");
        }
        try {
            JarFile jar = new JarFile(new File(getClass().getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .getPath()));
            jar.stream().map(JarEntry::getName).filter(i -> i.startsWith("module/bft.gz/report/")).forEach(ImportRepo::insReport);
        }catch (IOException e) {
            log.error(e.fillInStackTrace()+"");
        }
        try {
            JarFile jar = new JarFile(new File(getClass().getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .getPath()));
            jar.stream().map(JarEntry::getName).filter(i -> i.startsWith("module/bft.gz/reports/")).forEach(ImportRepo::insReports);
        }catch (IOException e) {
            log.error(e.fillInStackTrace()+"");
        }
    }



}
