package sample;

import org.slf4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ImportRepo {
    private static ArrayList<String> arrayLists=null;
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ImportRepo.class);

    public void startImportFile()  throws IOException{
        log.info("startImportFile()");
        //server SP
        String folderFileRepo = Controller3.FILES.replaceAll(".zip(.*)","");
        String report = "/"+folderFileRepo+"/module/bft.gz/report/";
        String reports = "/"+folderFileRepo+"/module/bft.gz/reports/";
        if(!new File(report).isDirectory() || !new File(reports).isDirectory()){
            new File(report).mkdirs();
            new File(reports).mkdirs();
        }
        log.info("report = "+report);
        log.info("report = "+reports);

        new  ImportRepo().insertFile("/module/bft.gz/report/",report);
        new  ImportRepo().insertFile("/module/bft.gz/reports/",reports);

//        //server tomcat
        String reportTom = "/"+folderFileRepo+"/apache-tomcat-6.0.29_BFT-1.0/webapps/azk/WEB-INF/module/bft.gz/report/";
        String reportsTom = "/"+folderFileRepo+"/apache-tomcat-6.0.29_BFT-1.0/webapps/azk/WEB-INF/module/bft.gz/reports/";
        if(!new File(reportTom).isDirectory() || !new File(reportsTom).isDirectory()){
            new File(reportTom).mkdirs();
            new File(reportsTom).mkdirs();
        }
        new  ImportRepo().insertFile("/module/bft.gz/report/",reportTom);
        new  ImportRepo().insertFile("/module/bft.gz/reports/",reportsTom);
//        //bpl client
        String libClient =  folderFileRepo+"/client/";/*"E:/test00000/"*/
        log.info(libClient);

        if(!new File(libClient).isDirectory() ){
            new File(libClient).mkdirs();
        }
     new  ImportRepo().insertFile("/lib/",libClient);

        try {
            log.info(" !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            JarFile jar = new JarFile(new File(getClass().getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .getPath()));
               jar.stream().map(JarEntry::getName).filter(i -> i.startsWith("lib/")).forEach(ImportRepo::ins);
        }catch (IOException e) {}

        try {
            log.info("=====arrayLists====size=== " + arrayLists.size());
            for (String s : arrayLists) {
                log.info("=====arrayLists=======" + s);
            }
        }catch (Exception e){
            log.error(e.fillInStackTrace()+"");
        }

    }
    public static void ins(String st){
        if(st.equals("")){
            log.info("st.equals"+st);
            arrayLists.add(st.replaceAll("lib/","")+"");
        }

        log.info("============"+st.replaceAll("lib/",""));

    }


    public void insertFile(String path, String newPath) {
        log.info("insertFile "+path+"  "+newPath);
        ArrayList arrayList = null;
        arrayList = arrayInputSt(ImportRepo.class.getResourceAsStream(path));
        log.info("insertFile arrayList "+arrayList+"size()  "+arrayList.size());
        for(Object s: arrayList){
            copyFileUsingStreams(ImportRepo.class.getResourceAsStream(
                    path+s),
                    new File(newPath+s));
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

    public void ReadMyResources() {
        JarFile jf = null;
        try {
            String s = new File(this.getClass().getResource("").getPath()).getParent().replaceAll("(!|file:\\\\)", "");
            jf = new JarFile(s);

            Enumeration<JarEntry> entries = jf.entries();
            while (entries.hasMoreElements()) {
                JarEntry je = entries.nextElement();
                if (je.getName().startsWith("/lib/")) {
                    log.info("____________"+je.getName());
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                jf.close();
            } catch (Exception e) {
            }
        }
    }


}
