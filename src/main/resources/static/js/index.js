
var index = 0;
var main_visuals = ['main_visual1.png', 'main_visual2.png'];

function change_img() {

    // ランダムに画像のファイル名を取得
    var main_visual = main_visuals[index];
//    var main_visual_path = 'D:\\OneDrive\\activity\\110.教育サービス〔作業〕\\300.案件\\2020-07～08月_アクシス様新人研修@鳥取\\110.開発演習\\モック\\img\\' + main_visual;
    var main_visual_path = 'img/' + main_visual;

    // メインビジュアルの画像要素を取得
    var main_visual_element = document.getElementById('main_visual');
    // ランダムに選択した画像のファイル名をメインビジュアルの画像要素に使用する。
    // console.log(main_visual_path);
    main_visual_element.setAttribute('src', main_visual_path);

    index = index + 1;
    if (index >= main_visuals.length) {
        index = 0;
    }

}