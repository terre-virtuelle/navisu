class Command
!!!128258.java!!!	Command(in cmd : String, inout navigationData : NavigationData)
        this.cmd = cmd;
        this.navigationData = navigationData;

!!!128386.java!!!	Command(in cmd : String, in arg : String)
        this.cmd = cmd;
        this.arg = arg;
!!!128514.java!!!	getCmd() : String
        return cmd;
!!!128642.java!!!	setCmd(in cmd : String) : void
        this.cmd = cmd;
!!!128770.java!!!	getNavigationData() : NavigationData
        return navigationData;
!!!128898.java!!!	setNavigationData(inout navigationData : NavigationData) : void
        this.navigationData = navigationData;
!!!129026.java!!!	getArg() : String
        return arg;
!!!129154.java!!!	setArg(in arg : String) : void
        this.arg = arg;
!!!129282.java!!!	toString() : String
        return "Command{" + "cmd=" + cmd + ", navigationData=" + navigationData + ", arg=" + arg + '}';
