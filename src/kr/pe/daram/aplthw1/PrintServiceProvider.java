package kr.pe.daram.aplthw1;

import java.util.*;
import java.lang.ref.*;

public class PrintServiceProvider
{
    static HashMap<KeyPair, String> m_services = new HashMap<> ();
    static HashMap<KeyPair, SoftReference<IPrintable>> m_cached = new HashMap<> ();

    public static boolean register ( KeyPair key, String clsname )
    {
        if ( m_services.containsKey ( key ) )
            return false;
        m_services.put ( key, clsname );
        return true;
    }

    public static IPrintable getService (KeyPair key )
    {
        if ( m_cached.containsKey ( key ) && m_cached.get ( key ).get () != null )
            return m_cached.get ( key ).get ();

        if ( !m_services.containsKey ( key ) )
            return null;

        try
        {
            Class cls = Class.forName ( m_services.get ( key ) );
            IPrintable instance = ( IPrintable ) cls.newInstance ();
            if ( m_cached.containsKey ( key ) )
                m_cached.replace( key, new SoftReference ( instance ) );
            else m_cached.put ( key, new SoftReference ( instance ) );

            return instance;
        }
        catch ( Exception e )
        {
            e.printStackTrace();
            return null;
        }
    }
}
