class VLW
!!!211330.java!!!	VLW(in device : String, in sentence : String, in dataTotalWaterDistance : float, in dataTripWaterDistance : float, in dataTotalGroundDistance : float, in dataTripGroundDistance : float)
        super(device, sentence);
        this.dataTotalWaterDistance = dataTotalWaterDistance;
        this.dataTripWaterDistance = dataTripWaterDistance;
        this.dataTotalGroundDistance = dataTotalGroundDistance;
        this.dataTripGroundDistance = dataTripGroundDistance;
!!!211586.java!!!	getDataTripGroundDistance() : float
        return dataTripGroundDistance;
!!!211714.java!!!	setDataTripGroundDistance(in dataTripGroundDistance : float) : void
        this.dataTripGroundDistance = dataTripGroundDistance;
!!!211842.java!!!	getDataTripWaterDistance() : float
        return dataTripWaterDistance;
!!!211970.java!!!	setDataTripWaterDistance(in dataTripWaterDistance : float) : void
        this.dataTripWaterDistance = dataTripWaterDistance;
!!!212098.java!!!	getDataTotalGroundDistance() : float
        return dataTotalGroundDistance;
!!!212226.java!!!	setDataTotalGroundDistance(in dataTotalGroundDistance : float) : void
        this.dataTotalGroundDistance = dataTotalGroundDistance;
!!!212354.java!!!	getDataTotalWaterDistance() : float
        return dataTotalWaterDistance;
!!!212482.java!!!	setDataTotalWaterDistance(in dataTotalWaterDistance : float) : void
        this.dataTotalWaterDistance = dataTotalWaterDistance;
!!!212610.java!!!	toString() : String
        return "VLW{" + "dataTotalWaterDistance=" + dataTotalWaterDistance + ", dataTripWaterDistance=" + dataTripWaterDistance + ", dataTotalGroundDistance=" + dataTotalGroundDistance + ", dataTripGroundDistance=" + dataTripGroundDistance + '}';
