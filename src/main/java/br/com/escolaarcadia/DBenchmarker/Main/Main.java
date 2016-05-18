package br.com.escolaarcadia.DBenchmarker.Main;


import java.nio.file.Paths;
import java.util.Scanner;

import br.com.escolaarcadia.DBenchmarker.util.Config;
import br.com.escolaarcadia.DBenchmarker.util.Log;

public class Main {

    private static final int MIN_ARGUMENTS = 1;


    public static void main(String[] args) throws ClassNotFoundException {
        validate(args);
        setup(args);

        new Main();
    }

    public Main() {

        BenchExecutor benchExecutor = new BenchExecutor(Config.getExecutorThreads());
        benchExecutor.execute();
        
        SystemAnalyzer systemAnalyzer = new SystemAnalyzer(benchExecutor, Config.getAnalyzerDelay());
        systemAnalyzer.start();
        
        Scanner scanner = new Scanner(System.in);		
        scanner.nextLine();
        
        benchExecutor.terminate();
        systemAnalyzer.terminate();
        scanner.close();
    }

    private static void validate(String[] args) {
        if(args.length >= MIN_ARGUMENTS)
           return;

        //If not enough arguments are provided
        Log.err("Not enough arguments to run this program.");
        Log.p("Usage: java -jar dbenchmarker /path/conf.properties");
        System.exit(1);
    }

    private static void setup(String[] args) {
        //Loading config file
        Config.load(Paths.get(args[0]));

    }

}
