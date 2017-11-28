$(document).ready( function () {
	 var table = $('#recordsTable').DataTable({
			"sAjaxSource": "/records",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
			      { "mData": "id"},
		          { "mData": "name" },
				  { "mData": "lastName" },
				  { "mData": "phone" },
			]
	 })
});