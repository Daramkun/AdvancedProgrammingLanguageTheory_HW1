package kr.pe.daram.aplthw1;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Daramkun on 2016-04-07.
 */
public class TestClass
{
    public static void main ( String [] args )
    {
        PrintServiceProvider.register ( new KeyPair ( "It's me,", "Mario!" ), "kr.pe.daram.aplthw1.TestPrintable1" );
        PrintServiceProvider.register ( new KeyPair ( "Requiescat", "in Pace." ), "kr.pe.daram.aplthw1.TestPrintable2" );
        PrintServiceProvider.register ( new KeyPair ( "Assassino!", "Ezio Auditore da Firenze" ), "kr.pe.daram.aplthw1.TestPrintable2" );
        PrintServiceProvider.register ( new KeyPair ( "Cake is a lie.", "GLaDOS" ), "kr.pe.daram.aplthw1.TestPrintable3" );
        //IPrintable tempPrintable = ()-> { System.out.println ( "SUPERHOT" ); };
        //PrintServiceProvider.register ( new KeyPair ( "SUPER", "HOT" ), tempPrintable.getClass ().getCanonicalName () );

        IPrintable printable1 = PrintServiceProvider.getService ( new KeyPair ( "It's me,", "Mario!" ) );
        printable1.printName ();
        System.out.println ( printable1 );

        IPrintable printable2 = PrintServiceProvider.getService ( new KeyPair ( "It's me,", "Mario!" ) );
        System.out.println ( "It is same thing?: " + ( printable1 == printable2 ) );
        //printable1 = printable2 = null;

        IPrintable printable3 = PrintServiceProvider.getService ( new KeyPair ( "Requiescat", "in Pace." ) );
        printable3.printName ();
        System.out.println ( printable3 );

        IPrintable printable4 = PrintServiceProvider.getService ( new KeyPair ( "Assassino!", "Ezio Auditore da Firenze" ) );
        System.out.println ( "It is same thing?: " + ( printable3 == printable4 ) );
        //printable3 = printable4 = null;

        generateOutOfMemory ();

        IPrintable printable5 = PrintServiceProvider.getService ( new KeyPair ( "It's me,", "Mario!" ) );
        printable5.printName ();
        System.out.println ( printable5 );

        IPrintable printable6 = PrintServiceProvider.getService ( new KeyPair ( "Cake is a lie.", "GLaDOS" ) );
        printable6.printName ();

        //IPrintable printable7 = PrintServiceProvider.getService ( new KeyPair ( "SUPER", "HOT" ) );
        //printable7.printName ();
    }

    private static void generateOutOfMemory ()
    {
        List<byte []> oomGenerator = new LinkedList<byte []> ();
        while ( true )
        {
            try { oomGenerator.add ( new byte [ 1024 * 1024 * 1024 ] ); }
            catch ( OutOfMemoryError err ) { System.out.println ( "ERR: Out of memory." ); break; }
            catch ( Exception ex ) { ex.printStackTrace (); }
        }
    }
}

class TestPrintable1 implements IPrintable
{
    @Override
    public void printName() {
        System.out.println ( "It's me, Mario!" );
    }
}

class TestPrintable2 implements IPrintable
{
    @Override
    public void printName() {
        System.out.println ( "Requiescat in Pace." );
    }
}

class TestPrintable3 implements IPrintable
{
    @Override
    public void printName() {
        System.out.println ( "You monster." );
    }
}