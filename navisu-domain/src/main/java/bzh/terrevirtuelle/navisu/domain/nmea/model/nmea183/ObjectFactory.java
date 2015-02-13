//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source. 
// G�n�r� le : 2013.11.07 � 02:44:28 PM CET 
//


package bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183;

import bzh.terrevirtuelle.navisu.domain.nmea.model.Sentences;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.BWR;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.GSA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.MWV;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.AAM;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.GPSSatellite;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.DBK;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.HDT;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.BOD;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.APA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.GGA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.DBT;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.BWW;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.GLL;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.HDG;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.MSK;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.HDM;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.GSV;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.MTA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.MWD;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.BEC;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.APB;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.MTW;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.DPT;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.BWC;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.DBS;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the generated package. 
 * <p>An ObjectFactory allows you to programatically 
 construct new instances of the Java representation 
 for XML content. The Java representation of XML 
 content can consist of schema derived interfaces 
 and classes representing the binding of schema 
 type definitions, element declarations and model 
 groups.  Factory methods for each of these are 
 provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _RTE_QNAME = new QName("", "RTE");
    private final static QName _GLL_QNAME = new QName("", "GLL");
    private final static QName _Sentences_QNAME = new QName("", "sentences");
    private final static QName _VTG_QNAME = new QName("", "VTG");
    private final static QName _XTE_QNAME = new QName("", "XTE");
    private final static QName _DPT_QNAME = new QName("", "DPT");
    private final static QName _HDG_QNAME = new QName("", "HDG");
    private final static QName _DBK_QNAME = new QName("", "DBK");
    private final static QName _BWC_QNAME = new QName("", "BWC");
    private final static QName _HDM_QNAME = new QName("", "HDM");
    private final static QName _MTW_QNAME = new QName("", "MTW");
    private final static QName _DBS_QNAME = new QName("", "DBS");
    private final static QName _GSA_QNAME = new QName("", "GSA");
    private final static QName _BOD_QNAME = new QName("", "BOD");
    private final static QName _ZDA_QNAME = new QName("", "ZDA");
    private final static QName _DBT_QNAME = new QName("", "DBT");
    private final static QName _HDT_QNAME = new QName("", "HDT");
    private final static QName _MWD_QNAME = new QName("", "MWD");
    private final static QName _Satellite_QNAME = new QName("", "satellite");
    private final static QName _BWR_QNAME = new QName("", "BWR");
    private final static QName _VPW_QNAME = new QName("", "VPW");
    private final static QName _GGA_QNAME = new QName("", "GGA");
    private final static QName _VLW_QNAME = new QName("", "VLW");
    private final static QName _BEC_QNAME = new QName("", "BEC");
    private final static QName _RSD_QNAME = new QName("", "RSD");
    private final static QName _BWW_QNAME = new QName("", "BWW");
    private final static QName _MSK_QNAME = new QName("", "MSK");
    private final static QName _VHW_QNAME = new QName("", "VHW");
    private final static QName _RMB_QNAME = new QName("", "RMB");
    private final static QName _RMC_QNAME = new QName("", "RMC");
    private final static QName _VBW_QNAME = new QName("", "VBW");
    private final static QName _GSV_QNAME = new QName("", "GSV");
    private final static QName _AAM_QNAME = new QName("", "AAM");
    private final static QName _MWV_QNAME = new QName("", "MWV");
    private final static QName _VWR_QNAME = new QName("", "VWR");
    private final static QName _APA_QNAME = new QName("", "APA");
    private final static QName _VWT_QNAME = new QName("", "VWT");
    private final static QName _APB_QNAME = new QName("", "APB");
    private final static QName _RMT_QNAME = new QName("", "RMT");
    private final static QName _MTA_QNAME = new QName("", "MTA");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GSV }
     * 
     * @return 
     */
    public GSV createGSV() {
        return new GSV();
    }

    /**
     * Create an instance of {@link RTE }
     * 
     */
    public RTE createRTE() {
        return new RTE();
    }

    /**
     * Create an instance of {@link GLL }
     * 
     */
    public GLL createGLL() {
        return new GLL();
    }

    /**
     * Create an instance of {@link Sentences }
     * 
     */
    public Sentences createSentences() {
        return new Sentences();
    }

    /**
     * Create an instance of {@link VTG }
     * 
     */
    public VTG createVTG() {
        return new VTG();
    }

    /**
     * Create an instance of {@link XTE }
     * 
     */
    public XTE createXTE() {
        return new XTE();
    }

    /**
     * Create an instance of {@link DPT }
     * 
     */
    public DPT createDPT() {
        return new DPT();
    }

    /**
     * Create an instance of {@link HDG }
     * 
     */
    public HDG createHDG() {
        return new HDG();
    }

    /**
     * Create an instance of {@link DBK }
     * 
     */
    public DBK createDBK() {
        return new DBK();
    }

    /**
     * Create an instance of {@link BWC }
     * 
     */
    public BWC createBWC() {
        return new BWC();
    }

    /**
     * Create an instance of {@link HDM }
     * 
     */
    public HDM createHDM() {
        return new HDM();
    }

    /**
     * Create an instance of {@link MTW }
     * 
     */
    public MTW createMTW() {
        return new MTW();
    }

    /**
     * Create an instance of {@link DBS }
     * 
     */
    public DBS createDBS() {
        return new DBS();
    }

    /**
     * Create an instance of {@link GSA }
     * 
     */
    public GSA createGSA() {
        return new GSA();
    }

    /**
     * Create an instance of {@link BOD }
     * 
     */
    public BOD createBOD() {
        return new BOD();
    }

    /**
     * Create an instance of {@link ZDA }
     * 
     */
    public ZDA createZDA() {
        return new ZDA();
    }

    /**
     * Create an instance of {@link DBT }
     * 
     */
    public DBT createDBT() {
        return new DBT();
    }

    /**
     * Create an instance of {@link HDT }
     * 
     */
    public HDT createHDT() {
        return new HDT();
    }

    /**
     * Create an instance of {@link MWD }
     * 
     */
    public MWD createMWD() {
        return new MWD();
    }

    /**
     * Create an instance of {@link GpsSatellite }
     * 
     */
    public GPSSatellite createGpsSatellite() {
        return new GPSSatellite();
    }

    /**
     * Create an instance of {@link BWR }
     * 
     */
    public BWR createBWR() {
        return new BWR();
    }

    /**
     * Create an instance of {@link VPW }
     * 
     */
    public VPW createVPW() {
        return new VPW();
    }

    /**
     * Create an instance of {@link GGA }
     * 
     */
    public GGA createGGA() {
        return new GGA();
    }

    /**
     * Create an instance of {@link VLW }
     * 
     */
    public VLW createVLW() {
        return new VLW();
    }

    /**
     * Create an instance of {@link BEC }
     * 
     */
    public BEC createBEC() {
        return new BEC();
    }

    /**
     * Create an instance of {@link RSD }
     * 
     */
    public RSD createRSD() {
        return new RSD();
    }

    /**
     * Create an instance of {@link BWW }
     * 
     */
    public BWW createBWW() {
        return new BWW();
    }

    /**
     * Create an instance of {@link MSK }
     * 
     */
    public MSK createMSK() {
        return new MSK();
    }

    /**
     * Create an instance of {@link VHW }
     * 
     */
    public VHW createVHW() {
        return new VHW();
    }

    /**
     * Create an instance of {@link RMB }
     * 
     */
    public RMB createRMB() {
        return new RMB();
    }

    /**
     * Create an instance of {@link RMC }
     * 
     */
    public RMC createRMC() {
        return new RMC();
    }

    /**
     * Create an instance of {@link VBW }
     * 
     */
    public VBW createVBW() {
        return new VBW();
    }

    /**
     * Create an instance of {@link AAM }
     * 
     */
    public AAM createAAM() {
        return new AAM();
    }

    /**
     * Create an instance of {@link MWV }
     * 
     */
    public MWV createMWV() {
        return new MWV();
    }

    /**
     * Create an instance of {@link VWR }
     * 
     */
    public VWR createVWR() {
        return new VWR();
    }

    /**
     * Create an instance of {@link APA }
     * 
     */
    public APA createAPA() {
        return new APA();
    }

    /**
     * Create an instance of {@link VWT }
     * 
     */
    public VWT createVWT() {
        return new VWT();
    }

    /**
     * Create an instance of {@link APB }
     * 
     */
    public APB createAPB() {
        return new APB();
    }

    /**
     * Create an instance of {@link RMT }
     * 
     */
    public RMT createRMT() {
        return new RMT();
    }

    /**
     * Create an instance of {@link MTA }
     * 
     */
    public MTA createMTA() {
        return new MTA();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RTE }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "RTE")
    public JAXBElement<RTE> createRTE(RTE value) {
        return new JAXBElement<RTE>(_RTE_QNAME, RTE.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GLL }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "GLL")
    public JAXBElement<GLL> createGLL(GLL value) {
        return new JAXBElement<GLL>(_GLL_QNAME, GLL.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sentences }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "sentences")
    public JAXBElement<Sentences> createSentences(Sentences value) {
        return new JAXBElement<Sentences>(_Sentences_QNAME, Sentences.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VTG }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "VTG")
    public JAXBElement<VTG> createVTG(VTG value) {
        return new JAXBElement<VTG>(_VTG_QNAME, VTG.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XTE }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "XTE")
    public JAXBElement<XTE> createXTE(XTE value) {
        return new JAXBElement<XTE>(_XTE_QNAME, XTE.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DPT }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "DPT")
    public JAXBElement<DPT> createDPT(DPT value) {
        return new JAXBElement<DPT>(_DPT_QNAME, DPT.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HDG }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "HDG")
    public JAXBElement<HDG> createHDG(HDG value) {
        return new JAXBElement<HDG>(_HDG_QNAME, HDG.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DBK }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "DBK")
    public JAXBElement<DBK> createDBK(DBK value) {
        return new JAXBElement<DBK>(_DBK_QNAME, DBK.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BWC }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "BWC")
    public JAXBElement<BWC> createBWC(BWC value) {
        return new JAXBElement<BWC>(_BWC_QNAME, BWC.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HDM }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "HDM")
    public JAXBElement<HDM> createHDM(HDM value) {
        return new JAXBElement<HDM>(_HDM_QNAME, HDM.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MTW }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "MTW")
    public JAXBElement<MTW> createMTW(MTW value) {
        return new JAXBElement<MTW>(_MTW_QNAME, MTW.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DBS }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "DBS")
    public JAXBElement<DBS> createDBS(DBS value) {
        return new JAXBElement<DBS>(_DBS_QNAME, DBS.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GSA }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "GSA")
    public JAXBElement<GSA> createGSA(GSA value) {
        return new JAXBElement<GSA>(_GSA_QNAME, GSA.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BOD }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "BOD")
    public JAXBElement<BOD> createBOD(BOD value) {
        return new JAXBElement<BOD>(_BOD_QNAME, BOD.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ZDA }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ZDA")
    public JAXBElement<ZDA> createZDA(ZDA value) {
        return new JAXBElement<ZDA>(_ZDA_QNAME, ZDA.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DBT }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "DBT")
    public JAXBElement<DBT> createDBT(DBT value) {
        return new JAXBElement<DBT>(_DBT_QNAME, DBT.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HDT }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "HDT")
    public JAXBElement<HDT> createHDT(HDT value) {
        return new JAXBElement<HDT>(_HDT_QNAME, HDT.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MWD }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "MWD")
    public JAXBElement<MWD> createMWD(MWD value) {
        return new JAXBElement<MWD>(_MWD_QNAME, MWD.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GpsSatellite }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "satellite")
    public JAXBElement<GPSSatellite> createSatellite(GPSSatellite value) {
        return new JAXBElement<GPSSatellite>(_Satellite_QNAME, GPSSatellite.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BWR }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "BWR")
    public JAXBElement<BWR> createBWR(BWR value) {
        return new JAXBElement<BWR>(_BWR_QNAME, BWR.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VPW }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "VPW")
    public JAXBElement<VPW> createVPW(VPW value) {
        return new JAXBElement<VPW>(_VPW_QNAME, VPW.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GGA }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "GGA")
    public JAXBElement<GGA> createGGA(GGA value) {
        return new JAXBElement<GGA>(_GGA_QNAME, GGA.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VLW }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "VLW")
    public JAXBElement<VLW> createVLW(VLW value) {
        return new JAXBElement<VLW>(_VLW_QNAME, VLW.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BEC }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "BEC")
    public JAXBElement<BEC> createBEC(BEC value) {
        return new JAXBElement<BEC>(_BEC_QNAME, BEC.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RSD }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "RSD")
    public JAXBElement<RSD> createRSD(RSD value) {
        return new JAXBElement<RSD>(_RSD_QNAME, RSD.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BWW }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "BWW")
    public JAXBElement<BWW> createBWW(BWW value) {
        return new JAXBElement<BWW>(_BWW_QNAME, BWW.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MSK }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "MSK")
    public JAXBElement<MSK> createMSK(MSK value) {
        return new JAXBElement<MSK>(_MSK_QNAME, MSK.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VHW }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "VHW")
    public JAXBElement<VHW> createVHW(VHW value) {
        return new JAXBElement<VHW>(_VHW_QNAME, VHW.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RMB }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "RMB")
    public JAXBElement<RMB> createRMB(RMB value) {
        return new JAXBElement<RMB>(_RMB_QNAME, RMB.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RMC }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "RMC")
    public JAXBElement<RMC> createRMC(RMC value) {
        return new JAXBElement<RMC>(_RMC_QNAME, RMC.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VBW }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "VBW")
    public JAXBElement<VBW> createVBW(VBW value) {
        return new JAXBElement<VBW>(_VBW_QNAME, VBW.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GSV }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "GSV")
    public JAXBElement<GSV> createGSV(GSV value) {
        return new JAXBElement<GSV>(_GSV_QNAME, GSV.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AAM }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "AAM")
    public JAXBElement<AAM> createAAM(AAM value) {
        return new JAXBElement<AAM>(_AAM_QNAME, AAM.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MWV }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "MWV")
    public JAXBElement<MWV> createMWV(MWV value) {
        return new JAXBElement<MWV>(_MWV_QNAME, MWV.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VWR }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "VWR")
    public JAXBElement<VWR> createVWR(VWR value) {
        return new JAXBElement<VWR>(_VWR_QNAME, VWR.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link APA }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "APA")
    public JAXBElement<APA> createAPA(APA value) {
        return new JAXBElement<APA>(_APA_QNAME, APA.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VWT }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "VWT")
    public JAXBElement<VWT> createVWT(VWT value) {
        return new JAXBElement<VWT>(_VWT_QNAME, VWT.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link APB }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "APB")
    public JAXBElement<APB> createAPB(APB value) {
        return new JAXBElement<APB>(_APB_QNAME, APB.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RMT }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "RMT")
    public JAXBElement<RMT> createRMT(RMT value) {
        return new JAXBElement<RMT>(_RMT_QNAME, RMT.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MTA }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "MTA")
    public JAXBElement<MTA> createMTA(MTA value) {
        return new JAXBElement<MTA>(_MTA_QNAME, MTA.class, null, value);
    }

}
