class DPT
!!!168706.java!!!	DPT(in device : String, in sentence : String, in depth : float, in offset : float)
        super(device, sentence);
        this.depth = depth;
        this.offset = offset;
!!!168962.java!!!	getDepth() : float
        return this.depth;
!!!169090.java!!!	setDepth(in value : float) : void
        this.depth = value;
!!!169218.java!!!	getOffset() : float
        return this.offset;
!!!169346.java!!!	setOffset(in value : float) : void
        this.offset = value;
!!!169474.java!!!	toString() : String
        return "DPT{" + "depth=" + depth + ", offset=" + offset + '}';
