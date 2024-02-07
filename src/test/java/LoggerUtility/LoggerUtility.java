package LoggerUtility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class LoggerUtility {
    private static final String suiteLogsPath = "target/logs/suite/";      // trebuie sa scoata fisierul separat , dar sa le si puna pe toate intr-unul
    private static final String regressionLogsPath = "target/logs/";

    private static final Logger logger = LogManager.getLogger();          // Cu cele 2 importuri de sus , ca altfel nu merge.

    public static synchronized void startTestCase(String testName) { //synchronized  = keyword care se aplica cand vrem sa blocam apelarea metodei in 2 thread-uri diferite

        // apoi trebuie sa pregatim filtrarea pe baza de fisiere , iar separatorul este numele testului , linia de mai jos
        ThreadContext.put("threadName", testName);     //ThreadContext este un conccurent hashmap , diferenta dintre el si un hasmap simplu este faptul ca poate sa ne tina la nivel de synchronized , informatiile pe care le introducem intr-un hashmap  ; adauga in hashmapul respectiv , numele testului
        logger.info("====================================== Execution started : " + testName + "================================================");    // info = ca sa ne dea un mesaj informativ
    }


    // metoda care logheaza oprirea unui test

    public static synchronized void endTestCase(String testName) {
        logger.info("====================================== Execution finished : " + testName + "================================================");
    }

    // metoda care logheaza o actiune din test

    public static synchronized void info(String message) {
        logger.info(Thread.currentThread().getName() + " : " + getCallInfo() + " " + message);      //Ne da numele threadului : ex: 1..2..3...4 etc, deci punem threadul , apoi clasa, apoi metoda(astea din getcallinfo) si mesajul
    }


    // metoda care returneaza informatii despre actiunea curenta

    // Ex: daca avem pachete multe si clase multe(in stanga), daca ne arata logul ca a cazut metoda click...., de unde stim care click? Vrem sa stim ca a cazut metoda X din clasa Y.. etc
    private static synchronized String getCallInfo() {
        String className = Thread.currentThread().getStackTrace()[3].getClassName(); //Thread-ul curent de executie, getStackTrace = informatiile care ne arata cand ne pica testul iar acesta este un Array si ne trebuie [] ca sa apelam un element dintr-un Array. Iar lista este cu "get". Si am pus 3; 1= informativ,2= eroare ; Iar getClassName ne da numele clasei curente de pe thread.
        String methodName = Thread.currentThread().getStackTrace()[3].getMethodName(); //Apoi scoatem methodname.
        return className + " : " + methodName + "==>";            // Apoi aceste 2 informatii vrem sa le lipim(sa le concatenam)
    }

    // metoda care returneaza mesaj de eroare

    public static synchronized void error(String message) {
        logger.error(Thread.currentThread().getName() + " : " + getCallInfo() + " " + message);
        logger.info("====================================== Test failed ================================================");
    }


    // metoda care pune tot continutul fisierelor intr-unul singur

    public static void mergeLogsIntoOne(){
        File dir = new File(suiteLogsPath);

        // Get list of all the files in form of String Array
        String[] fileNames = dir.list();
        try {
            // create object of PrintWriter for output file
            PrintWriter pw = new PrintWriter(regressionLogsPath + "output.log");


            // loop for reading the contents of all the files
            // in the directory GeeksForGeeks
            for (String fileName : fileNames) {
                if(fileName.contains(".log")) {
                    // create instance of file from Name of
                    // the file stored in string Array
                    File f = new File(dir, fileName);

                    // create object of BufferedReader
                    BufferedReader br = new BufferedReader(new FileReader(f));
                    pw.println("Contents of file " + fileName);

                    // Read from current file
                    String line = br.readLine();
                    while (line != null) {

                        // write to the output file
                        pw.println(line);
                        line = br.readLine();
                    }
                    pw.flush();
                }
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
