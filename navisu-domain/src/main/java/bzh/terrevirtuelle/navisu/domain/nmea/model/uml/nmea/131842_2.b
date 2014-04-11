class GPSSatellite
!!!174338.java!!!	GPSSatellite(in satellitePRNNumber : int, in elevationDegrees : int, in azimuthDegrees : int, in snr : int)
        this.satellitePRNNumber = satellitePRNNumber;
        this.elevationDegrees = elevationDegrees;
        this.azimuthDegrees = azimuthDegrees;
        this.snr = snr;
!!!174466.java!!!	GPSSatellite()

!!!174594.java!!!	toString() : String
        StringBuffer buffer = new StringBuffer();
        buffer.append("Satellite n° ").append(satellitePRNNumber).append(" : ");
        buffer.append("elevationDegrees : ").append(elevationDegrees);
        buffer.append(", azimuthDegrees : ").append(azimuthDegrees);
        buffer.append(", SNR : ").append(snr);

        return new String(buffer);
!!!174722.java!!!	getSnr() : int
        return snr;
!!!174850.java!!!	setSnr(in snr : int) : void
        this.snr = snr;
!!!174978.java!!!	getAzimuthDegrees() : int
        return azimuthDegrees;
!!!175106.java!!!	setAzimuthDegrees(in azimuthDegrees : int) : void
        this.azimuthDegrees = azimuthDegrees;
!!!175234.java!!!	getElevationDegrees() : int
        return elevationDegrees;
!!!175362.java!!!	setElevationDegrees(in elevationDegrees : int) : void
        this.elevationDegrees = elevationDegrees;
!!!175490.java!!!	getSatellitePRNNumber() : int
        return satellitePRNNumber;
!!!175618.java!!!	setSatellitePRNNumber(in satellitePRNNumber : int) : void
        this.satellitePRNNumber = satellitePRNNumber;
