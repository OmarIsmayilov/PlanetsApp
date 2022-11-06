package com.omarismayilov.recycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.FirebaseDatabase
import com.omarismayilov.recycleview.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private val titles = arrayOf(
        "Merkuri","Venera","Yer","Mars","Yupiter","Saturn","Uran","Neptun"
    )

    private val descriptions = arrayOf(
        "Günəş sistemində yerləşən ən kiçik və Günəşə ən yaxın olan planet. Yer qrupu planetlərə aid olan Merkuri Günəş ətrafında ən sürətlə dövr edən planetdir və 88 günə tam bir dəfə...",
        "Günəş sistemində yerləşən ikinci planet. Günəş ətrafında hərəkətini 224,7 Yer gününə başa vurur.Günəş sistemində yerləşən digər planetlərə nisbətən ən uzun öz oxu ətrafında...",
        "Günəşə yaxınlığına görə Günəş sistemindəki üçüncü planet və həyat aşkar olunan yeganə göy cismi. Radiometrik tanışlıq və digər dəlillərə görə Yer 4,5 milyard il əvvəl yaran...",
        "Günəş sisteminin Günəşə yaxınlığına görə dördüncü və kiçikliyinə görə Merkuridən sonra ikinci planeti. Adını Roma mifologiyasının müharibə tanrısından alan bu planetin səth...",
        "Günəş sistemində Günəşdən məsafəsinə görə beşinci, böyüklüyünə görə birinci planet. Yupiter Günəşin kütləsinin mində birinə bərabər olsa da, digər planetlərin cəmi kütləsin...",
        "Günəş sistemində Yupiterdən sonra ən böyük ikinci, Günəşdən məsafəsinə görə altıncı planet. Saturn radiusu Yerin radiusundan təqribən doqquz dəfə böyük olan qaz nəhəngidir...",
        "Günəşə yaxınlığına görə yeddinci planet. Günəş sistemində radiusuna görə üçüncü, kütləsinə görə dördüncü ən böyük planetdir. Uranın tərkibi Neptunun tərkibi ilə eynidir...",
        "Günəş sistemində səkkizinci və məlum olan ən uzaq planet. Nəhəng planetlər qrupuna daxil olan Neptun Günəş sistemində diametrinə görə dördüncü, kütləsinə görə isə üçüncü yer...",
    )

    private val infos = arrayOf(
        "Günəş sistemində yerləşən ən kiçik və Günəşə ən yaxın olan planet. Yer qrupu planetlərə aid olan Merkuri Günəş ətrafında ən sürətlə dövr edən planetdir və 88 günə tam bir dəfə dövr edir. Buna baxmayaraq Merkuri öz oxu ətrafında çox yavaş hərəkət edir.",
        "Günəş sistemində yerləşən ikinci planet. Günəş ətrafında hərəkətini 224,7 Yer gününə başa vurur.Günəş sistemində yerləşən digər planetlərə nisbətən ən uzun öz oxu ətrafında dönmə perioduna (243 gün) sahibdir və digər planetlərdən fərqli olaraq əks istiqamətdə fırlanır. Venera təbii peykə sahib deyildir. Onun adı Roma mifologiyasındakı sevgi və gözəllik ilahəsi olan Veneradan gəlir. Venera gecə səmasında Aydan sonra ən parlaq şəkildə görünən ikinci təbii göy cismidir",
        "Günəşə yaxınlığına görə Günəş sistemindəki üçüncü planet və həyat aşkar olunan yeganə göy cismi. Radiometrik tanışlıq və digər dəlillərə görə Yer 4,5 milyard il əvvəl yaranmışdır. Yerin cazibə qüvvəsi kainatdakı digər cisimlərə, xüsusən də Yerin yeganə təbii peyki olan Aya və Günəşə qarşılıqlı təsir göstərir. Yer 365 gün ərzində Günəş ətrafında öz orbiti boyu hərəkət edir. Bu müddət ərzində Yer öz oxu ətrafında 365 (366) dəfə fırlanır.",
        "Günəş sisteminin Günəşə yaxınlığına görə dördüncü və kiçikliyinə görə Merkuridən sonra ikinci planeti. Adını Roma mifologiyasının müharibə tanrısından alan bu planetin səthində dəmir oksidinin geniş yayılması ona qırmızı görünüş verir və o, tez-tez \"qırmızı planet\" kimi təsvir edilir. Mars nazik atmosferi və həm ayın çarpışmadan sonra yaranan kraterləri, həm də Yerin vulkanları, dərələri, səhraları, qütb buz örtüklərini xatırladan səth xüsusiyyətləri olan terrestrial (əsasən silikat süxurları və metallardan ibarət) planetdir.",
        "Günəş sistemində Günəşdən məsafəsinə görə beşinci, böyüklüyünə görə birinci planet. Yupiter Günəşin kütləsinin mində birinə bərabər olsa da, digər planetlərin cəmi kütləsindən 2,5 dəfə çox kütləyə malik qaz nəhəngidir. Yupiter qədim dövrlərdən etibarən astronomlara məlumdur. Adının mənşəyi Roma mifologiyasında baş tanrı hesab olunan Yupiterdən qaynaqlanır. Yupiter Yerdə kölgə yarada biləcək qədər parlaq işığa sahibdir. Gecə səmasında Ay və Veneradan sonra parlaqlığına görə üçüncü təbii səma cismidir.",
        "Günəş sistemində Yupiterdən sonra ən böyük ikinci, Günəşdən məsafəsinə görə altıncı planet. Saturn radiusu Yerin radiusundan təqribən doqquz dəfə böyük olan qaz nəhəngidir. Saturnun sıxlığı Yerin sıxlığının səkkizdə biri qədər olsa da, həcmi Yerin həcmindən 95 dəfə çoxdur. Planetin adı Roma mifologiyasında tanrı olan Saturndan gəlir və astronomik simvolu (♄) Saturnun orağını təmsil edir.",
        "Günəşə yaxınlığına görə yeddinci planet. Günəş sistemində radiusuna görə üçüncü, kütləsinə görə dördüncü ən böyük planetdir. Uranın tərkibi Neptunun tərkibi ilə eynidir, hər ikisində də eyni kimyəvi elementlər mövcuddur, bu tərkib hissələri onları böyük qaz nəhəngləri olan Yupiter və Saturndan fərqləndirir. Bu səbəblə, alimlər Uran və Neptunu qaz nəhənglərindən ayırmaq üçün onları \"buz nəhəngləri\" kimi təsnif edirlər. Uranın atmosferinin əsasən hidrogen və helium tərkibi ilə Saturn və Yupiterin atmosferinə oxşarlığına baxmayaraq, ammonyak, metan və başqa hidrokarbonlarla birlikdə tərkibində daha çox buz mövcuddur.",
        "Günəş sistemində səkkizinci və məlum olan ən uzaq planet. Nəhəng planetlər qrupuna daxil olan Neptun Günəş sistemində diametrinə görə dördüncü, kütləsinə görə isə üçüncü yeri tutur. Neptunun kütləsi Yerin kütləsindən 17 dəfə daha çoxdur. Bu göstərici Neptunun əkizi hesab olunan Uranda 15 dəfəyə uyğun gəlir. Neptun Günəşdən 30,1 AV uzaqlıqda (4,50×109 km) yerləşir və Günəş ətrafında hərəkətini 164,8 ilə tamamlayır. Neptun adı Roma mifologiyasından qaynaqlanır. Neptun Roma mifologiyasında dəniz tanrısı hesab olunurdu. Planetin astronomik simvolu olan ♆ işarəsi də Neptunun əlində təsvir olunan üçdişli mizraqla bağlıdır.",

        )
    private val images = arrayOf(
        R.drawable.merkuri,
        R.drawable.venera,
        R.drawable.earthpng,
        R.drawable.mars,
        R.drawable.jupiter,
        R.drawable.saturn,
        R.drawable.uran,
        R.drawable.neptun,
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadRecycleViewItems()


    }

    private fun loadRecycleViewItems() {
        binding.planetsRv.layoutManager = LinearLayoutManager(this)

        val planetList:ArrayList<modelItem> = ArrayList()

        for (i in titles.indices){
            val model = modelItem(titles[i],descriptions[i],images[i],infos[i])
            planetList.add(model)
        }

        val adapterItem = adapter(this,planetList)
        binding.planetsRv.adapter = adapterItem

    }
}