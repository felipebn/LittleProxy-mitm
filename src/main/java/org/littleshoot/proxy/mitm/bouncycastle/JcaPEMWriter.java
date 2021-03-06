package org.littleshoot.proxy.mitm.bouncycastle;

import java.io.IOException;
import java.io.Writer;

import org.bouncycastle.openssl.PEMEncryptor;
import org.bouncycastle.openssl.jcajce.JcaMiscPEMGenerator;
import org.bouncycastle.util.io.pem.PemGenerationException;
import org.bouncycastle.util.io.pem.PemObjectGenerator;
import org.bouncycastle.util.io.pem.PemWriter;

/**
 * General purpose writer for OpenSSL PEM objects based on JCA/JCE classes.
 *
 * Backported from bouncycastle:bcpkix-jdk15on:1.51 by Felipebn
 *
 */
public class JcaPEMWriter
    extends PemWriter
{
    /**
     * Base constructor.
     *
     * @param out output stream to use.
     */
    public JcaPEMWriter(Writer out)
    {
        super(out);
    }

    /**
     * @throws java.io.IOException
     */
    public void writeObject(
        Object  obj)
        throws IOException
    {
        writeObject(obj, null);
    }

    /**
     * @param obj
     * @param encryptor
     * @throws java.io.IOException
     */
    public void writeObject(
        Object  obj,
        PEMEncryptor encryptor)
        throws IOException
    {
        try
        {
            super.writeObject(new JcaMiscPEMGenerator(obj, encryptor));
        }
        catch (PemGenerationException e)
        {
            if (e.getCause() instanceof IOException)
            {
                throw (IOException)e.getCause();
            }

            throw e;
        }
    }

    @Override
	public void writeObject(
        PemObjectGenerator obj)
        throws IOException
    {
        super.writeObject(obj);
    }
}