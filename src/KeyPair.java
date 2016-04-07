class KeyPair
{
    String m_nickname, m_author;

    public String getNickname () { return m_nickname; }
    public String getAuthor () { return m_author; }

    @Override
    public boolean equals ( Object other )
    {
        if ( !( other instanceof KeyPair ) )
            return false;

        KeyPair otherPair = ( KeyPair ) other;
        return m_nickname.equals ( otherPair.m_nickname ) && m_author.equals ( otherPair.m_author );
    }

    @Override
    public int hashCode ()
    {
        int nickhash = m_nickname.hashCode (), authorhash = m_author.hashCode ();
        return ( ( nickhash & 0xffff0000 ) + ( ( nickhash << 16 ) & 0xffff0000 ) ) +
                ( ( authorhash & 0xffff ) + ( ( authorhash >> 16 ) & 0xffff ) );
    }
}