<%-- 
    Author     : Alejandro
    Description: Incluye scripts necesarios para mostrar el editor avanzado de texto.
--%>

<s:url id="urlTiniMce" value="/scripts/tinymce/jscripts/tiny_mce/tiny_mce.js" />
<s:url id="urlFckEditor" value="/scripts/fckeditor/fckeditor.js" />

<script src="${urlTiniMce}"></script>
<script src="${urlFckEditor}"></script>

<script type="text/javascript">
    
    function editor(textareaId, height) {

        var oFCKeditor = new FCKeditor(textareaId) ;
        
        oFCKeditor.BasePath	= '../scripts/fckeditor/';
        oFCKeditor.Height	= height;
        oFCKeditor.Value	= '';
        
        oFCKeditor.ReplaceTextarea();
    }
    
</script>
