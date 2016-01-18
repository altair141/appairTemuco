package capaNegocio;

import android.graphics.Color;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolygonOptions;

/**
 * Created by altair141 on 30-12-2015.
 */
public class Posicion {
    public static final PolygonOptions POLIGONO = new PolygonOptions()
            .add(new LatLng(-38.728543, -72.604491),
                    new LatLng(-38.728086, -72.603773),
                    new LatLng(-38.727584, -72.603464),
                    new LatLng(-38.727108, -72.602602),
                    new LatLng(-38.726829, -72.602241),
                    new LatLng(-38.726673, -72.602166),
                    new LatLng(-38.725743, -72.601990),
                    new LatLng(-38.726116, -72.598508),
                    new LatLng(-38.727570, -72.596007),
                    new LatLng(-38.729950, -72.596532),//10
                    new LatLng(-38.730244, -72.595966),
                    new LatLng(-38.730330, -72.592270),
                    new LatLng(-38.728848, -72.589364),
                    new LatLng(-38.728837, -72.589173),
                    new LatLng(-38.728024, -72.585733),
                    new LatLng(-38.726297, -72.582408),
                    new LatLng(-38.725459, -72.579736),
                    new LatLng(-38.722004, -72.579219),
                    new LatLng(-38.720969, -72.575589),
                    new LatLng(-38.717664, -72.569041),//20
                    new LatLng(-38.717473, -72.570859),
                    new LatLng(-38.715643, -72.571771),
                    new LatLng(-38.711325, -72.569057),
                    new LatLng(-38.709243, -72.572796),
                    new LatLng(-38.704167, -72.556185),
                    new LatLng(-38.700554, -72.554685),
                    new LatLng(-38.700044, -72.553268),
                    new LatLng(-38.696407, -72.551519),
                    new LatLng(-38.695933, -72.549136),
                    new LatLng(-38.694446, -72.547653),//30
                    new LatLng(-38.688772, -72.546088),
                    new LatLng(-38.689154, -72.539725),
                    new LatLng(-38.685087, -72.533955),
                    new LatLng(-38.686146, -72.532790),
                    new LatLng(-38.687951, -72.531958),
                    new LatLng(-38.678720, -72.519220),
                    new LatLng(-38.680034, -72.516346),
                    new LatLng(-38.683935, -72.515964),
                    new LatLng(-38.685232, -72.512125),
                    new LatLng(-38.686879, -72.512024),//40
                    new LatLng(-38.687986, -72.512865),
                    new LatLng(-38.705386, -72.547482),
                    new LatLng(-38.708011, -72.549996),
                    new LatLng(-38.715664, -72.555140),
                    new LatLng(-38.724161, -72.561065),
                    new LatLng(-38.730709, -72.571858),
                    new LatLng(-38.725015, -72.574004),
                    new LatLng(-38.726423, -72.575937),
                    new LatLng(-38.733235, -72.588676),
                    new LatLng(-38.734967, -72.577818),//50
                    new LatLng(-38.743015, -72.586812),
                    new LatLng(-38.743028, -72.587383),
                    new LatLng(-38.740723, -72.602838),
                    new LatLng(-38.745220, -72.608950),
                    new LatLng(-38.752240, -72.609205),
                    new LatLng(-38.752808, -72.609688),
                    new LatLng(-38.753463, -72.610607),
                    new LatLng(-38.754820, -72.634463),
                    new LatLng(-38.756017, -72.639290),
                    new LatLng(-38.753369, -72.638528),//60
                    new LatLng(-38.753331, -72.641123),
                    new LatLng(-38.755847, -72.642255),
                    new LatLng(-38.755934, -72.641867),
                    new LatLng(-38.756654, -72.642098),
                    new LatLng(-38.756567, -72.643351),
                    new LatLng(-38.757487, -72.655152),
                    new LatLng(-38.757141, -72.660643),
                    new LatLng(-38.754425, -72.661387),
                    new LatLng(-38.744352, -72.657635),//se repite con la 70
                    new LatLng(-38.744352, -72.657635),//70
                    new LatLng(-38.739208, -72.652907),
                    new LatLng(-38.732747, -72.663687),
                    new LatLng(-38.723964, -72.660333),
                    new LatLng(-38.722477, -72.656407),
                    new LatLng(-38.720273, -72.655221),
                    new LatLng(-38.718060, -72.652040),
                    new LatLng(-38.717868, -72.652023),
                    new LatLng(-38.717492, -72.655217),
                    new LatLng(-38.716942, -72.658666),
                    new LatLng(-38.717431, -72.661693),//80
                    new LatLng(-38.717166, -72.666946),
                    new LatLng(-38.717287, -72.668804),
                    new LatLng(-38.714352, -72.668786),
                    new LatLng(-38.706961, -72.665968),
                    new LatLng(-38.707392, -72.664655),
                    new LatLng(-38.706932, -72.663650),
                    new LatLng(-38.707181, -72.662394),
                    new LatLng(-38.708440, -72.660795),
                    new LatLng(-38.708663, -72.658203),
                    new LatLng(-38.708106, -72.656470),//90
                    new LatLng(-38.709377, -72.651048),
                    new LatLng(-38.709942, -72.650605),
                    new LatLng(-38.711000, -72.650800),
                    new LatLng(-38.712251, -72.651250),
                    new LatLng(-38.713834, -72.645328),
                    new LatLng(-38.719300, -72.647788),
                    new LatLng(-38.720746, -72.645228),
                    new LatLng(-38.721743, -72.643631),
                    new LatLng(-38.722603, -72.639716),
                    new LatLng(-38.722855, -72.635950),//100
                    new LatLng(-38.724189, -72.633136),
                    new LatLng(-38.725110, -72.631910),
                    new LatLng(-38.725446, -72.629567),
                    new LatLng(-38.726506, -72.627286),
                    new LatLng(-38.726531, -72.625722),
                    new LatLng(-38.727064, -72.623064),
                    new LatLng(-38.727917, -72.620564),
                    new LatLng(-38.728806, -72.620700),
                    new LatLng(-38.728065, -72.613574),
                    new LatLng(-38.728612, -72.611645),//110
                    new LatLng(-38.728242, -72.610684),
                    new LatLng(-38.729987, -72.606212),
                    new LatLng(-38.733607, -72.600315),
                    new LatLng(-38.729544, -72.599517),
                    new LatLng(-38.728463, -72.604356),
                    new LatLng(-38.728543, -72.604491))
            .strokeColor(Color.RED)
            .fillColor(0x7F00FF00)//0xFF00FF00
            ;
    public static final PolygonOptions POLIGONO2 = new PolygonOptions()
            .add(new LatLng(-38.758272, -72.603731),
                    new LatLng(-38.756811, -72.599619),
                    new LatLng(-38.758118, -72.598352),
                    new LatLng(-38.759864, -72.601056),
                    new LatLng(-38.760155, -72.601820),
                    new LatLng(-38.758272, -72.603731)
            )
            .strokeColor(Color.RED)
            .fillColor(0x7F00FF00)//0xFF00FF00
            ;
    public static final PolygonOptions POLIGONO3 = new PolygonOptions()
            .add(new LatLng(-38.773420, -72.606934),
                    new LatLng(-38.774255, -72.602251),
                    new LatLng(-38.774723, -72.598144),
                    new LatLng(-38.776086, -72.598208),
                    new LatLng(-38.777900, -72.598513),
                    new LatLng(-38.777192, -72.601712),
                    new LatLng(-38.776821, -72.606509),
                    new LatLng(-38.773420, -72.606934)
            )
            .strokeColor(Color.RED)
            .fillColor(0x7F00FF00)//0xFF00FF00
            ;

    public static final PolygonOptions POLIGONO4 = new PolygonOptions()
            .add(new LatLng(-38.778008, -72.595630),
                    new LatLng(-38.778723, -72.594671),
                    new LatLng(-38.779412, -72.591443),
                    new LatLng(-38.779412, -72.590033),
                    new LatLng(-38.778090, -72.588426),
                    new LatLng(-38.776056, -72.588457),
                    new LatLng(-38.774593, -72.584595),
                    new LatLng(-38.772298, -72.580544),
                    new LatLng(-38.771514, -72.581280),
                    new LatLng(-38.769463, -72.580793),//10
                    new LatLng(-38.769774, -72.583479),
                    new LatLng(-38.767052, -72.586969),
                    new LatLng(-38.770752, -72.586718),
                    new LatLng(-38.772544, -72.585354),
                    new LatLng(-38.773626, -72.589147),
                    new LatLng(-38.774249, -72.589058),
                    new LatLng(-38.774462, -72.591434),
                    new LatLng(-38.775329, -72.591293),
                    new LatLng(-38.775432, -72.592892),
                    new LatLng(-38.776834, -72.592295),//20
                    new LatLng(-38.778008, -72.595630)
            )
            .strokeColor(Color.RED)
            .fillColor(0x7F00FF00)//0xFF00FF00
            ;
}
