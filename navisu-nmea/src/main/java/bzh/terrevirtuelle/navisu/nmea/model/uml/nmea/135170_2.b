class RMT
!!!204802.java!!!	RMT(in device : String, in sentence : String, in versions : String, in romChecksum : String)
        super(device, sentence);
        this.versions = versions;
        this.romChecksum = romChecksum;
!!!205058.java!!!	getRomChecksum() : String
        return romChecksum;
!!!205186.java!!!	setRomChecksum(in romChecksum : String) : void
        this.romChecksum = romChecksum;
!!!205314.java!!!	getVersions() : String
        return versions;
!!!205442.java!!!	setVersions(in versions : String) : void
        this.versions = versions;
