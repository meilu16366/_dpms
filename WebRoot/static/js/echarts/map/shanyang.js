(function (root, factory) {if (typeof define === 'function' && define.amd) {define(['exports', 'echarts'], factory);} else if (typeof exports === 'object' && typeof exports.nodeName !== 'string') {factory(exports, require('echarts'));} else {factory({}, root.echarts);}}(this, function (exports, echarts) {var log = function (msg) {if (typeof console !== 'undefined') {console && console.error && console.error(msg);}};if (!echarts) {log('ECharts is not Loaded');return;}if (!echarts.registerMap) {log('ECharts Map is not loaded');return;}echarts.registerMap('shanyang', {"type":"FeatureCollection","features":[{"type":"Feature","id":"611024","properties":{"name":"山阳县","cp":[109.882289,33.532172],"childNum":1},"geometry":{"type":"Polygon","coordinates":["@@@@@@A@@@A@@A@@A@@@@@@@@BA@@@@B@@A@@@@@A@@@@@@AA@@@@AA@@@@A@@@@@@@@A@@B@@A@@@AB@@@B@BB@@BA@@@@B@@@@@@B@@@@@B@@B@@KL@B@BIJOLCBA@MA@@@@@@@@@@[CICA@MEQBMBWBUAMAC@KAOCKAWIGAOCSBYPCJGH@@A@IFMBKCECOEQCKBOFABSJYLEBGHABKDMFKB[FUDWFULGBGBGBMAIAYG]IQCK@WAM@MBSBYCQAKAEAIBSD[LEDEHKRKBUBOEQIK@A@EF@TCNIFMJC@W@EAA@A@A@@BCBABC@E@A@A@BB@@@@BBB@@@@@ABA@A@@BABAB@BA@CBA@ABA@A@C@A@AA@AA@AAA@@@A@ABA@@@CAA@E@A@@@AD@@@DBFBBB@@BB@BB@BBB@D@@BD@@@BA@A@AB@BC@A@@BA@@B@B@@A@CB@@AB@@@B@B@B@B@@@BB@@@@@@BAB@BABA@A@@@A@A@@@A@@@A@@@BDDFBB@B@@@B@B@@@@@@D@@@DBB@B@BB@@@B@B@@AB@BBBABAH@DA@@@@@A@C@EBEAE@A@AAC@AA@@A@A@C@ABGD@@@@@B@@BB@B@B@BADBB@B@@@B@FA@@@A@A@ABA@EB@@@B@BAB@BBFADBB@@@BD@@@BABA@@B@BAD@BADCB@@AD@@@B@BBBBBBB@BBBB@@@B@B@@AD@B@@@@B@@BDBBB@@@@@B@@AB@B@B@B@@DDDBBBFB@@BB@@@B@F@B@B@@@BAD@B@B@@A@CAC@A@@@ABABABA@@@@@@@AA@A@A@@@ACAAAAC@ACCA@@@C@A@@@CDCBA@AB@@ABAB@@@B@BBBBBBD@@DB@BB@@D@B@B@BB@@B@@@@BB@@@BB@B@B@B@@A@@B@@D@B@B@B@@ABAD@@BBBB@B@D@@BB@BBB@B@BABBB@B@B@@B@@BBAFAF@B@@@BD@D@B@BBBB@BBBBBBB@B@BBB@BB@@B@@BB@B@@@B@@@@BBBBBB@@B@@@B@@@B@@@B@@@@A@@@AFABABBB@@@B@BAB@DAFBH@@AD@B@B@BB@BB@@B@B@B@D@BA@@BB@BBB@@D@FDF@@B@@BADAHC@@@@B@AD@@BBBB@B@@BBAB@@@B@B@@@B@@@B@B@@@D@B@@AD@BA@@@A@@@A@AAA@A@@@ADA@ABAD@@ED@@A@@@AB@AA@@@CBA@CB@@ABABAB@@@BCBAD@@A@AB@B@BAB@@@BA@@@@@AB@B@BC@ABABA@@BA@A@@BAB@BAB@@@B@DBB@B@BB@@B@@@@AB@B@@A@@BA@A@@B@@C@@@AB@@@BB@@B@BA@@B@@@DA@@B@@ABA@@@A@@DAB@@AB@@@BAD@@BB@BDD@@DBB@@@@BBBBB@DBB@B@@BDBB@BABBB@@BDBDBBBB@@BBB@BADAFADA@@@@BDBB@@BBBB@BBBB@BB@@AB@B@B@D@B@@@BAB@@C@@@AB@D@@AD@BABCB@@A@@DAB@@@@@BA@@@@BAB@B@@@@BBD@@BBB@@AB@DA@@BAD@@AB@B@BAB@BAD@@@DB@@B@BAB@@@@@B@B@@CBAB@B@B@B@B@B@@BB@@@@A@ABA@AB@@AB@@@B@@A@A@@BAB@@@B@B@B@BBD@@DBD@@@@DBB@B@@@@@B@D@B@B@B@B@@@@B@BB@BD@DB@@B@B@@@DB@@FD@@@B@BDF@@B@BBB@BBBBDB@BD@BBB@B@@B@@@BB@@BAB@@@B@@BBBBDDBD@@@@B@@BB@B@B@BBB@@@HF@@D@BBB@B@@@BA@@D@B@DAB@B@BAB@@@B@@BB@@@B@@AB@B@@A@@FAB@FAB@DBB@F@BAB@@AB@B@@@D@H@@@B@@DBBBB@@DBB@BBBDBDDD@BBBBBDB@@B@DAD@B@@@DBD@B@@AB@D@BBD@B@B@BB@@B@B@@@D@D@B@@ABAB@@@B@@@BBFDFD@@HCB@BBAB@B@@H@@@BBBBB@DB@@@@@BDD@BB@BBD@BBB@B@D@B@@@BBD@BBB@BA@@DCBCBA@A@@B@B@B@F@B@@@B@BDB@B@@@DB@@F@BBB@B@B@B@D@B@B@BB@@D@B@BBB@BAB@@@@C@CBA@ABA@@AA@@B@BA@ADC@ADC@@@A@A@ABCBABCFGBABABABC@@@ADA@AB@@@B@B@B@DCB@DA@@BABABA@AB@BB@@@BB@BBD@D@@BB@BBB@BBDBBB@@B@B@B@B@BBB@@@@@@ABA@AB@@A@CBCAC@A@ABA@@DAB@@@BA@A@A@@@ABA@@BC@AAC@CAE@A@@@AAC@@BA@@JBD@BAB@@BB@BB@B@B@BBB@@@@@@B@JAB@BAB@@AB@BAB@B@B@@@B@D@B@BAD@@@@@@B@@@@@BAB@BAB@@@@AB@@@@@F@BA@@BCB@B@B@@BD@B@BBB@@@@B@B@B@@@DB@@@AB@B@B@B@@AB@BA@@@A@A@@@@DA@ABABCB@@AB@B@F@@ABA@@@@@AB@DA@@BA@@@AB@@@D@DAB@@@@@B@B@B@B@DDBB@@@@D@B@B@@@@@BABAD@@ABCBAB@@A@@B@BBDBB@@@DBB@BBB@BD@BB@@BD@BB@B@B@F@@@@BB@@AB@@@D@B@B@BABADABAB@@@BB@B@BBB@@BDB@B@@@BBFB@BB@@@B@B@B@D@B@@B@BDB@@@@B@@@B@@B@B@D@@@F@B@@@BBBBB@@BB@@BBB@@BF@@BBDB@F@@BB@B@BBB@@@@BBF@D@D@@@DBB@B@@@D@DBBAB@BB@@@@DAB@DBBCB@B@@@@@D@@@@@@@BBB@DDBB@@B@@CBA@@B@DB@@BA@@B@@BB@@@BCBA@@@@B@B@@@BB@@@@@@BABCBA@@FAB@BBB@BBBBB@BD@@BBD@@@@BB@@A@@D@@@D@B@BB@D@B@BB@@@B@F@@@B@@@B@D@B@DB@@B@DCB@B@BADA@@@A@@B@DAB@BAB@@@@A@@BAB@BA@A@@BBB@@@BAB@B@@@BAB@@A@@B@D@@@BB@BBBB@B@BBDA@@@BB@@@BA@@BA@AB@BA@@BA@@D@@@B@BABA@@@@B@BB@@@BB@B@B@@@@A@@@AB@DAFABA@@FAB@@@B@D@B@B@@ABABAB@B@B@B@B@@@@CB@@@@A@A@A@@@A@A@C@A@A@@@@@A@A@@BAB@B@@ABBBAB@@@BAB@B@B@BAB@BABAB@@A@@B@B@D@B@@@BA@@@@BAB@B@B@@@B@@@DC@@@@BA@@B@B@DBFBBABCBA@@BABABABA@@BA@AB@BABA@@@CB@@@@A@@@AD@B@@@DCB@B@D@BBBBB@B@D@B@@@B@B@B@BB@@@@@ABAB@@A@EB@@ABE@A@@A@AACA@@AA@AAA@C@E@AAC@@AA@A@@@ABC@A@EBC@AA@AAAA@A@@BA@@@A@C@@@@@AAC@@@A@@@AAAAA@A@@@C@A@C@@@A@AAC@@@@@ABB@@B@B@B@B@B@DBB@@@@@@A@@@A@@@E@A@A@C@C@AB@@A@A@@@A@C@AAC@A@@BA@@B@@@B@@@B@@ABA@@BAB@@@B@B@@BD@B@BC@C@A@@CA@A@@@AAC@EBA@@@AB@BC@@B@B@@@BB@@B@B@@@B@@@D@BA@@B@D@B@@@FAB@@@BABA@@F@DAB@B@BA@@DCDA@AAA@A@A@A@A@@@@BA@@@A@E@@@@@AAABA@C@C@AB@B@B@@@BC@A@@@ABAB@@@@E@@BA@@DAB@@AB@@BB@@@D@DAD@@@@BDDBBBB@@B@@@B@B@BBDB@@BB@@@@BA@A@@DCB@B@@@@A@@AA@CAEBEACBE@ABA@A@@B@@A@@@A@A@A@A@C@A@A@A@AA@@A@@B@@CB@@@@@DAB@B@@ABAB@@@BEBA@ABABA@AB@@@B@@BB@@BB@@@B@@AB@@@@@B@@C@E@@B@@@B@@@BA@@B@B@BBBAB@@@BABC@ABA@@BA@AB@@A@AAA@ABABA@ABA@A@C@A@@B@@@B@B@B@@@BB@@@BDB@B@B@B@@@@ABA@@@AB@@AB@BA@@@@@@B@B@B@@@B@B@D@F@B@FBBDBB@DBB@D@@@BBBD@@B@@BBB@BB@@DB@BF@B@BBDB@@B@@B@@@@A@@BE@A@@@A@@@AB@@A@A@@AA@@BA@A@A@A@A@@@AB@BAB@@@BABCBE@@FE@A@AB@@ABADA@CBA@@@A@A@A@A@A@AA@AC@A@CAA@C@A@@@AB@AA@ABABA@@@A@@@AB@@ABA@A@@BA@A@@@A@A@@B@@A@@A@@A@@AC@@ACA@@@@@@AAC@AAA@A@A@ADA@@@A@A@@@@@C@A@A@A@@BA@@AA@@@A@@@A@AAAAA@AAC@A@@ACCE@@@A@@B@DAB@@@@ABA@C@@AAAC@@@@@A@A@A@@BABCBAB@AA@@@@BA@A@@BA@A@A@@BA@ABA@@D@BAB@@BBDBB@@BB@D@B@@@B@DBB@BBB@@DFBDBBBD@BDB@BD@B@@@BBDBBBBBBBB@@AHABAB@@BBBD@DBB@B@B@@@@AB@@A@C@A@CAA@CA@@A@@@CBCB@@C@AAA@AACAA@A@A@CB@BA@ABA@AB@@BBDBBBBB@@@D@D@@@F@B@@@BCB@@@B@B@B@H@F@@@@B@@A@@BAB@@BD@BB@BBDDBB@@B@@@B@BBB@@@DB@@B@D@D@@@B@@BB@@BB@B@@@B@@AB@BC@@BBF@@@BABABAB@@A@A@A@ABA@@@CBE@C@A@C@@B@@@B@BAFB@@BBBBBB@@@@BAB@@@@A@@@AB@DA@@B@BBB@B@B@D@BBB@BBBBDD@@B@@@B@D@BAF@B@BAB@B@B@@@B@@@BA@@@@B@DAF@B@@ADAB@@ABA@AB@@@B@B@DA@@BBB@@@DAB@BABB@@B@B@BB@@B@BAB@@@@A@A@@AA@@@A@C@@@@AAAAAC@A@AA@@@CA@AAAAA@@CBA@@B@BAB@BADAB@@AB@B@BABCBA@AA@@@@@C@@@A@@@AAA@@@A@@@A@ABAB@@A@EAAAA@@@C@A@@AA@@AA@A@@B@BAB@B@DAD@B@@A@@AA@A@@@A@A@C@@@@CA@A@@@AAA@A@@A@@AC@AAA@@@@A@@@ABC@A@ABABA@A@@@A@@C@A@@A@@A@@@A@CA@@AA@@@AFA@@@@D@@A@@BA@ADCBA@@D@BAB@@@@A@AAACCCAA@A@A@@@AA@@BCA@@A@@A@@@@A@@@ABA@A@@AC@A@AB@@A@@@AA@CCC@CAA@A@CAA@@@A@@@CBC@C@A@@AAAEEAAC@AAA@ACC@C@AAC@@@C@A@E@A@A@A@A@A@@@A@@@AA@@@AAA@@BAB@@AB@AE@A@@@A@A@@@A@ADA@AB@D@B@B@@@BAB@@@@BB@BBB@BB@@BB@@F@BB@@DDB@DBD@B@B@BBB@@BB@B@D@D@B@B@BB@@B@@@BAFCDAB@DAD@@@@@BB@@BB@@B@@B@@BBB@BBB@@@@A@A@A@AAI@G@A@A@C@A@A@ABABA@ABA@@@AB@B@B@@@A@@AB@@@@@@A@@@@@@@ABA@@B@@@@@BA@@@@@@@@BA@@@@@@BABB@@@A@@@@B@@@@@@@@@B@@@@@@@BA@@@BB@@@@A@@D@@@@@B@@@@@@@@B@@B@@@@@BA@@@A@@@@B@@@@@@AB@A@@@@@B@BA@@@@@A@@B@@AB@@@@@@@B@@@AA@@@@A@@@BC@@@A@@@A@@@@@@@A@@@@@@B@@@@@AA@@A@@@AA@@@@B@BA@@BA@@@AB@B@@@@A@@@@@@B@@@@@@@A@@@@A@@@@@AHCAAAA@AAA@@@@@@@@@@@A@@@@@@@@@@A@@@@@@@@@@@A@@@@@@@@@@@@@@AA@@A@@@@@@@@@@@@@@A@@@@@@@@B@@@@@@@@@@@@@@A@@@@@@AB@@A@@@@@@@@@@A@@@@@@@A@@@@BA@@@@@@@@@@@@@@A@@@@@@@@@A@@B@@@@@@@@@@@@@@A@@A@@@A@@@@@@@@@@@@B@@@@@@@@@@@@ABA@A@@@@@@@@@@@@@@@@A@@@@@@BA@@@A@@@@@A@@@@@A@@@A@@@@@@@@A@@A@@@@@@@A@@@@@@@@@@@AA@@@@B@@@@@@@@@@@B@@@@AA@@A@@@A@@@@@@@@@@@AA@@@A@A@@@@@@@A@@@@@@@@A@@@@BA@@@@@@@AA@@@@@@A@@A@@@@A@@@@@@@@@@@@@@@@AA@@@@@@@A@@@@@B@@A@@@@@@@A@@@@@A@@@@A@@@@@@AA@@@A@@@@@@@@@@A@@@@B@@@@@D@@@@@@@@@@@@A@@@@@@@@@@B@@@@@@@@@@@@@@@@A@@AA@@@@@ABA@@@@@@@@@A@@@@@@A@@@@@A@@@@@@@@@@@@@@@@@@@@@@@@@BA@@@@B@@@@@@@@@@@@@@A@@AAA@A@@BA@A@A@@BA@CBE@CDMFA@A@IAEA@@EAEAA@AAAAC@@@AAECI@QDSHG@@@@@@@@@A@@@@@@@@B@@A@@@@@@@@@@@@@@A@@@@@@@A@@@@@@@@@@@@@@@@@A@@@@@@A@@@@@@@@B@@@@@B@@@@A@@@@B@@@@@@@@@@@@@A@@@@@@AAA@@@@A@@@@@@@B@@@@@@A@@@@@@B@@@AA@@@@@@@@@@@@@@A@@AB@@@@@@@@@@@@@@@@@B@@A@@A@@@@@@@@@@@@@@A@@@@@@@@@@BB@@@@@@B@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@A@@@QBA@AAAAA@A@C@@@@@@@@@B@@@@@@@@@AB@@@@@@@@@@@@AB@@A@@B@@@@@B@@@@@@B@@@B@@@BA@@B@@@@@@@@@@B@@@@@@@@@BA@@@@@@@@@@@@BB@@@@@B@@@@@B@@B@@@@@@@@@@@B@@@@@@@@@BA@@@@@@@@@AA@@AA@@@@AA@@@B@@@@@@@@@@@@@B@@@@@@@@@@@BAB@@@@@@AB@@@@@@@@@@@AA@@@@@@@@@@@@A@@@@@A@@AC@@@@ABA@A@AAA@C@A@AA@@AAAA@AAA@AAAAAAAA@AAC@@@A@AAABC@A@C@C@@@@@@@@AA@@@@@@@@@B@@@@@@A@@@@A@@@@@@@@@A@@@@B@@A@@A@@@@@@@@B@@@@@@A@@@@@@@@@@A@@@@@@@@@@@@@@BA@@@@@@@@@@@A@@A@@@@@@@@@@@@@A@@B@@@@@@@@@@@@@B@@@B@B@@@@A@@@@@@B@@@@@@@@AA@@@@@@@A@@@A@@@@@AA@@A@@AA@AA@@@@@@@@@@@@B@@@@@B@@@@@B@@@@@@A@@@@BA@@@@AAGAKF[FABC@A@ABABA@@@CBC@C@A@ABA@A@A@C@ABA@M@OCKACAKAQGICGAM@OAQDIFGHIJINKFGDAB"],"encodeOffsets":[[112839,33982]]}}],"UTF8Encoding":true});}));