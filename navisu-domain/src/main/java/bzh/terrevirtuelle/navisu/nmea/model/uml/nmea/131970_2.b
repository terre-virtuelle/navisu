class GSA
!!!175746.java!!!	GSA(in device : String, in sentence : String, in autoOrManualSelection : String, in dimensionFix : int, in pdop : float, in hdop : float, in vdop : float)
        super(device, sentence);
        this.autoOrManualSelection = autoOrManualSelection;
        this.dimensionFix = dimensionFix;
        this.pdop = pdop;
        this.hdop = hdop;
        this.vdop = vdop;
!!!176002.java!!!	getHdop() : float
        return hdop;
!!!176130.java!!!	setHdop(in hdop : float) : void
        this.hdop = hdop;
!!!176258.java!!!	getPdop() : float
        return pdop;
!!!176386.java!!!	setPdop(in pdop : float) : void
        this.pdop = pdop;
!!!176514.java!!!	getVdop() : float
        return vdop;
!!!176642.java!!!	setVdop(in vdop : float) : void
        this.vdop = vdop;
!!!176770.java!!!	getAutoOrManualSelection() : String
        return autoOrManualSelection;
!!!176898.java!!!	setAutoOrManualSelection(in autoOrManualSelection : String) : void
        this.autoOrManualSelection = autoOrManualSelection;
!!!177026.java!!!	getDimensionFix() : int
        return dimensionFix;
!!!177154.java!!!	setDimensionFix(in dimensionFix : int) : void
        this.dimensionFix = dimensionFix;
!!!177282.java!!!	addPRNOfSatelliteUsed(in prn : int) : void
        listPRNsOfSatellitesUsed.add(prn);
!!!177410.java!!!	getPRNOfSatelliteUsed(in pos : int) : int
        return listPRNsOfSatellitesUsed.get(pos);
!!!177538.java!!!	getNumPRNOfSatelliteUsed() : int
        return listPRNsOfSatellitesUsed.size();
!!!177666.java!!!	getListPRNsOfSatellitesUsed() : List<Integer>
        return listPRNsOfSatellitesUsed;
!!!177794.java!!!	toString() : String
        return "GSA{" + "autoOrManualSelection=" + autoOrManualSelection + ", dimensionFix=" + dimensionFix + ", listPRNsOfSatellitesUsed=" + listPRNsOfSatellitesUsed + ", PDOP=" + pdop + ", HDOP=" + hdop + ", VDOP=" + vdop + '}';
