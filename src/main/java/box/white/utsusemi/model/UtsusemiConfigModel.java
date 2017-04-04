package box.white.utsusemi.model;

/**
 * 空蝉で利用する設定情報を保持する
 */
public class UtsusemiConfigModel {

    private String databaseType;
    private String host;
    private String port;
    private String dbName;
    private String user;
    private String password;
    private String outputDirectory;
    private String pathToDrivers;
    private String charset;

    /**
     * @return the databaseType
     */
    public String getDatabaseType() {
        return databaseType;
    }

    /**
     * @param databaseType
     *            the databaseType to set
     */
    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType;
    }

    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host
     *            the host to set
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @return the port
     */
    public String getPort() {
        return port;
    }

    /**
     * @param port
     *            the port to set
     */
    public void setPort(String port) {
        this.port = port;
    }

    /**
     * @return the dbName
     */
    public String getDbName() {
        return dbName;
    }

    /**
     * @param dbName
     *            the dbName to set
     */
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user
     *            the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the outputDirectory
     */
    public String getOutputDirectory() {
        return outputDirectory;
    }

    /**
     * @param outputDirectory
     *            the outputDirectory to set
     */
    public void setOutputDirectory(String outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    /**
     * @return the pathToDrivers
     */
    public String getPathToDrivers() {
        return pathToDrivers;
    }

    /**
     * @param pathToDrivers
     *            the pathToDrivers to set
     */
    public void setPathToDrivers(String pathToDrivers) {
        this.pathToDrivers = pathToDrivers;
    }

    /**
     * @return the charset
     */
    public String getCharset() {
        return charset;
    }

    /**
     * @param charset
     *            the charset to set
     */
    public void setCharset(String charset) {
        this.charset = charset;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((charset == null) ? 0 : charset.hashCode());
        result = prime * result
                + ((databaseType == null) ? 0 : databaseType.hashCode());
        result = prime * result + ((dbName == null) ? 0 : dbName.hashCode());
        result = prime * result + ((host == null) ? 0 : host.hashCode());
        result = prime * result
                + ((outputDirectory == null) ? 0 : outputDirectory.hashCode());
        result = prime * result
                + ((password == null) ? 0 : password.hashCode());
        result = prime * result
                + ((pathToDrivers == null) ? 0 : pathToDrivers.hashCode());
        result = prime * result + ((port == null) ? 0 : port.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UtsusemiConfigModel other = (UtsusemiConfigModel) obj;
        if (charset == null) {
            if (other.charset != null)
                return false;
        } else if (!charset.equals(other.charset))
            return false;
        if (databaseType == null) {
            if (other.databaseType != null)
                return false;
        } else if (!databaseType.equals(other.databaseType))
            return false;
        if (dbName == null) {
            if (other.dbName != null)
                return false;
        } else if (!dbName.equals(other.dbName))
            return false;
        if (host == null) {
            if (other.host != null)
                return false;
        } else if (!host.equals(other.host))
            return false;
        if (outputDirectory == null) {
            if (other.outputDirectory != null)
                return false;
        } else if (!outputDirectory.equals(other.outputDirectory))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (pathToDrivers == null) {
            if (other.pathToDrivers != null)
                return false;
        } else if (!pathToDrivers.equals(other.pathToDrivers))
            return false;
        if (port == null) {
            if (other.port != null)
                return false;
        } else if (!port.equals(other.port))
            return false;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        return true;
    }
}
