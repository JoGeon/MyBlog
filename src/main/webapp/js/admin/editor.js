var editor = function() {
    
    var okindEditor = null;
    
    var options = {
        width : '100%',
        height :'400px',
        langType : 'zh_CN',
        filterMode : true,
        llowFileManager : true,
        resizeType: 1
    };

    function CreatEditor() {
        okindEditor = KindEditor.create('textarea[name="content"]', options);
    };
    
    function getEditorText() {
        if (okindEditor != null) return okindEditor.html();
        return null;
    };
    
    function setEditorText(strhtml) {
        if (okindEditor != null) return okindEditor.html(strhtml);
        return null;
    }

    return {
        FCreatEditor : CreatEditor,
        FgetEditorText : getEditorText,
        FsetEditorText : setEditorText
    };
}();

