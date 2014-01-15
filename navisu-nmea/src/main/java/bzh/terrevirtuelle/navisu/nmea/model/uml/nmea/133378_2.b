class RTE
!!!205570.java!!!	getIndex() : String
        return routeIndex;
!!!205698.java!!!	setIndex(in routeIndex : String) : void
        this.routeIndex = routeIndex;
!!!205826.java!!!	RTE(in device : String, in sentence : String, in totalNumberOfsentence : int, in sentenceNumber : int, in type : String, inout wpts : List<String>)
        super(device, sentence);
        this.totalNumberOfsentence = totalNumberOfsentence;
        this.sentenceNumber = sentenceNumber;
        this.type = type;
        this.waypoints.addAll(wpts);
        String last = waypoints.remove(waypoints.size() - 1);
        if (waypoints.size() > 1) {
            routeIndex = waypoints.remove(0);
        }
        StringTokenizer st = new StringTokenizer(last, "*");
        if (st.countTokens() > 1) {
            waypoints.add(st.nextToken());
        }
!!!206082.java!!!	getRouteIndex() : String
        return routeIndex;
!!!206210.java!!!	setRouteIndex(in routeIndex : String) : void
        this.routeIndex = routeIndex;
!!!206338.java!!!	getWaypoints() : List<String>
        return waypoints;
!!!206466.java!!!	setWaypoints(inout waypoints : List<String>) : void
        this.waypoints = waypoints;
!!!206594.java!!!	getType() : String
        return type;
!!!206722.java!!!	setType(in type : String) : void
        this.type = type;
!!!206850.java!!!	getSentenceNumber() : int
        return sentenceNumber;
!!!206978.java!!!	setSentenceNumber(in sentenceNumber : int) : void
        this.sentenceNumber = sentenceNumber;
!!!207106.java!!!	getTotalNumberOfsentence() : int
        return totalNumberOfsentence;
!!!207234.java!!!	setTotalNumberOfsentence(in totalNumberOfsentence : int) : void
        this.totalNumberOfsentence = totalNumberOfsentence;
!!!207362.java!!!	toString() : String
        return "RTE{" + "totalNumberOfsentence=" + totalNumberOfsentence + ", sentenceNumber=" + sentenceNumber + ", type=" + type + ", waypoints=" + waypoints + ", routeIndex=" + routeIndex + '}';
