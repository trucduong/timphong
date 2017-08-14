var APP_JS = {
		cmps:{}
};

/**
 * init
 */
$(function() {
	APP_JS.buildUpload = function(fieldId) {
		var resource = JSON.parse($('#'+fieldId).val());
		var cmp = new FileUploadCmp(fieldId, resource);
		cmp.build();
		APP_JS.cmps[fieldId] = cmp;
	};

	APP_JS.buildEditor = function(editorId) {
		var currentValue=$('#'+editorId).val();
		$('#'+editorId).after('<div id="'+editorId+'_editor">'+currentValue+'</div>');
		
	};
	
	APP_JS.setEditorValue = function(editorId, value) {
		
	}

});

$(function() {
	
});