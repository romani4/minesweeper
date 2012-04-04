package ru.nsu.ccfit.romanov.minesweeper;

import java.io.File;
import java.io.IOException;

/**
 * Таблица рекордов (не работает)
 * @author Dmitriy Romanov
 */
public class Records {

    private File file;

//    private HashMap<ModelSettings, HashMap<Long,String> > records = new HashMap<ModelSettings, HashMap<Long, String>>();
    public Records(String fileName) throws IOException, ClassNotFoundException {
//        this.file = new File(fileName);
//        if( file.canRead() ){
//            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file) );
//            records = (HashMap<ModelSettings, HashMap<Long,String> >)in.readObject();
//        }
    }

    public void addRecord(String name, long time, ModelSettings settings) {
//        HashMap<Long,String> table = records.get(settings);
//        if( null == table ){
//            table = new HashMap<Long,String>();
//            records.put(settings, table);
//        }
//        table.put(time, name);
    }

    public void save() throws IOException {
//        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file) );
//        try{
//            out.writeObject(records);
//        } finally {
//            out.close();
//        }
    }
}
