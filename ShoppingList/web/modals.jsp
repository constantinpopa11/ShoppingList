<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!-- Share Modal -->
<div class="modal fade" id="shareModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Share Link</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <input class="linkField" type="text" spellcheck="false" value="${initParam['WEBSERVER_LOCATION']}/Share?shareLink=${activeSL.shareLink}"/>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<!-- Remove Item Modal -->
<div class="modal fade" id="removeItemModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Confirm action</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>Are you sure you want to remove this item from the list?</p>
            </div>
            <div class="modal-footer">
                <form action="EditShoppingList" method="GET">
                    <input type="hidden" name="removePid" value="">
                    <input type="hidden" name="action" value="removeItem">

                    <input type="submit" class="btn btn-primary" value="Remove" />
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                </form>

            </div>
        </div>
    </div>
</div>

<!-- Edit Qty Modal -->
<div class="modal fade" id="updateModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Update quantity</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="EditShoppingList" method="GET">
                <div class="modal-body">
                    <label for=foo>Quantity</label>
                    <input type=text name="qty" required="required" autocomplete="off" />
                </div>
                <div class="modal-footer">

                    <input type="hidden" name="updatePid" value="">
                    <input type="hidden" name="action" value="update">

                    <input type="submit" class="btn btn-primary" value="Update" />
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>    
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Remove SL Modal -->
<div class="modal fade" id="removeSLModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Confirm action</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>Are you sure you want to remove this shopping list? All items will be lost.</p>
            </div>
            <div class="modal-footer">
                <form action="EditShoppingList" method="GET">
                    <input type="hidden" name="action" value="removeSL">

                    <input type="submit" class="btn btn-primary" value="Remove" />
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                </form>

            </div>
        </div>
    </div>
</div>