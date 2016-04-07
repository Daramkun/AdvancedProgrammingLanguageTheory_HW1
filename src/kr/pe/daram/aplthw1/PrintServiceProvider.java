package kr.pe.daram.aplthw1;

import java.util.*;
import java.lang.ref.*;

public class PrintServiceProvider
{
    final static HashMap<KeyPair, String> m_services = new HashMap<> ();
    //final static HashMap<KeyPair, SoftReference<IPrintable>> m_cached = new HashMap<> ();
    final static Map<KeyPair, IPrintable> m_cached = new WeakHashMap<> ();

    public static boolean register ( KeyPair key, String classname )
    {
        if ( m_services.containsKey ( key ) )
            return false;
        m_services.put ( key, classname );
        return true;
    }

    public static IPrintable getService ( KeyPair key )
    {
        if ( m_cached.containsKey ( key ) )
            return m_cached.get ( key );

        if ( !m_services.containsKey ( key ) )
            return null;

        try
        {
            Class cls = Class.forName ( m_services.get ( key ) );
            IPrintable instance = IPrintable.class.cast ( cls.newInstance () );
            if ( m_cached.containsKey ( key ) )
                m_cached.replace( key, instance );
            else m_cached.put ( key, instance );

            return instance;
        }
        catch ( Exception e )
        {
            e.printStackTrace();
            return null;
        }
    }
}
