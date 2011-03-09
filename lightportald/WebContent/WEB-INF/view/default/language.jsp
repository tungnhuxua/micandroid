<%
/**
 * Light Portal
 *
 * Copyright (c) 2009, Light Portal, Inc or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Light Portal, Inc.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 *
 */
%>
<%@ include file="/common/taglibs.jsp"%>
<fmt:bundle basename="resourceBundle">
<var id="VIEW_MODE" title= '<fmt:message key="_VIEW_MODE"/>'></var>
<var id="EDIT_MODE" title='<fmt:message key="_EDIT_MODE"/>'></var>
<var id="HELP_MODE" title='<fmt:message key="_HELP_MODE"/>'></var>
<var id="CONFIG_MODE" title='<fmt:message key="_CONFIG_MODE"/>'></var>
<var id="REFRESH_PORTLET" title= '<fmt:message key="_REFRESH_PORTLET"/>'></var>
<var id="MOVE_UP" title= '<fmt:message key="_MOVE_UP"/>'></var>
<var id="MOVE_DOWN" title= '<fmt:message key="_MOVE_DOWN"/>'></var>
<var id="MINIMIZED" title='<fmt:message key="_MINIMIZED"/>'></var>
<var id="RESTORE_MINIMIZED" title='<fmt:message key="_RESTORE_MINIMIZED"/>'></var>
<var id="MAXIMIZED" title='<fmt:message key="_MAXIMIZED"/>'></var>
<var id="POPOUT" title='<fmt:message key="_POPOUT"/>'></var>
<var id="POPIN" title='<fmt:message key="_POPIN"/>'></var>
<var id="RESTORE_MAXIMIZED" title='<fmt:message key="_RESTORE_MAXIMIZED"/>'></var>
<var id="RESTORE" title='<fmt:message key="_RESTORE"/>'></var>
<var id="CLOSE" title='<fmt:message key="_CLOSE"/>'></var>
<var id="MENU_HOME" title= '<fmt:message key="_MENU_HOME"/>'></var>
<var id="MENU_OPTIONS" title= '<fmt:message key="_MENU_OPTIONS"/>'></var>
<var id="MENU_LAYOUT" title= '<fmt:message key="_MENU_LAYOUT"/>'></var>
<var id="MENU_ADD_TAB" title= '<fmt:message key="_MENU_ADD_TAB"/>'></var>
<var id="MENU_ADD_SUBTAB" title= '<fmt:message key="_MENU_ADD_SUBTAB"/>'></var>
<var id="MENU_ADD_CONTENT" title= '<fmt:message key="_MENU_ADD_CONTENT"/>'></var>
<var id="MENU_SIGN_UP" title= '<fmt:message key="_MENU_SIGN_UP"/>'></var>
<var id="TITLE_SIGN_UP" title= '<fmt:message key="_TITLE_SIGN_UP"/>'></var>
<var id="MENU_SIGN_IN" title= '<fmt:message key="_MENU_SIGN_IN"/>'></var>
<var id="MENU_BLOG" title= '<fmt:message key="portlet.title.blog"/>'></var>
<var id="MENU_SEARCH" title= '<fmt:message key="portlet.button.search"/>'></var>
<var id="MENU_MY_ACCOUNT" title= '<fmt:message key="_MENU_MY_ACCOUNT"/>'></var>
<var id="MENU_MY_PICTURE" title= '<fmt:message key="_MENU_MY_PICTURE"/>'></var>
<var id="MENU_MY_MUSIC" title= '<fmt:message key="_MENU_MY_MUSIC"/>'></var>
<var id="MENU_MY_MUSIC_PLAYER" title= '<fmt:message key="_MENU_MY_MUSIC_PLAYER"/>'></var>
<var id="MENU_MY_FILE" title= '<fmt:message key="_MENU_MY_FILE"/>'></var>
<var id="MENU_MY_BLOG" title= '<fmt:message key="_MENU_MY_BLOG"/>'></var>
<var id="MENU_MY_FAVOURITE" title= '<fmt:message key="_MENU_MY_FAVOURITE"/>'></var>
<var id="MENU_MY_VIEWED" title= '<fmt:message key="_MENU_MY_VIEWED"/>'></var>
<var id="MENU_MY_RECOMMENDED" title= '<fmt:message key="_MENU_MY_RECOMMENDED"/>'></var>
<var id="MENU_RANK_ME" title='<fmt:message key="_MENU_RANK_ME"/>'></var>
<var id="MENU_MESSAGE_INBOX" title= '<fmt:message key="_MENU_MESSAGE_INBOX"/>'></var>
<var id="MENU_MESSAGE_SENT" title= '<fmt:message key="_MENU_MESSAGE_SENT"/>'></var>
<var id="MENU_MESSAGE_SEND" title='<fmt:message key="_MENU_MESSAGE_SEND"/>'></var>
<var id="MENU_MESSAGE_FORWARD" title= '<fmt:message key="_MENU_MESSAGE_FORWARD"/>'></var>
<var id="MENU_FRIEND_REQUEST" title= '<fmt:message key="_MENU_FRIEND_REQUEST"/>'></var>
<var id="MENU_INVITE_FRIEND" title= '<fmt:message key="_MENU_INVITE_FRIEND"/>'></var>
<var id="MENU_RANK" title= '<fmt:message key="_MENU_RANK"/>'></var>
<var id="MENU_GROUP" title= '<fmt:message key="_MENU_GROUP"/>'></var>
<var id="MENU_TODO" title= '<fmt:message key="_MENU_TODO"/>'></var>
<var id="MENU_CALENDAR" title= '<fmt:message key="_MENU_CALENDAR"/>'></var>
<var id="MENU_GROUP_EDIT" title= '<fmt:message key="_MENU_GROUP_EDIT"/>'></var>
<var id="MENU_GROUP_INVITE" title= '<fmt:message key="_MENU_GROUP_INVITE"/>'></var>
<var id="MENU_GROUP_MEMBERS" title= '<fmt:message key="_MENU_GROUP_MEMBERS"/>'></var>
<var id="MENU_GROUP_BULLETIN" title= '<fmt:message key="_MENU_GROUP_BULLETIN"/>'></var>
<var id="MENU_GROUP_PICTURES" title= '<fmt:message key="_MENU_GROUP_PICTURES"/>'></var>
<var id="MENU_GROUP_UPLOAD_PICTURE" title= '<fmt:message key="_MENU_GROUP_UPLOAD_PICTURE"/>'></var>
<var id="MENU_SIGN_OUT" title= '<fmt:message key="_MENU_SIGN_OUT"/>'></var>
<var id="MENU_TURN_OFF" title= '<fmt:message key="_MENU_TURN_OFF"/>'></var>
<var id="MENU_EXPAND_ALL" title= '<fmt:message key="_MENU_EXPAND_ALL"/>'></var>
<var id="MENU_COLLAPSE_ALL" title= '<fmt:message key="_MENU_COLLAPSE_ALL"/>'></var>
<var id="MENU_ABOUT" title='<fmt:message key="_MENU_ABOUT"/>'></var>
<var id="MENU_FAQ" title='<fmt:message key="_MENU_FAQ"/>'></var>
<var id="MENU_TERMS" title='<fmt:message key="_MENU_TERMS"/>'></var>
<var id="MENU_PRIVACY" title='<fmt:message key="_MENU_PRIVACY"/>'></var>
<var id="MENU_CONTACT_US" title='<fmt:message key="_MENU_CONTACT_US"/>'></var>
<var id="LABEL_NEW_TAB" title= '<fmt:message key="_LABEL_NEW_TAB"/>'></var>
<var id="LABEL_SUBJECT" title= '<fmt:message key="_LABEL_SUBJECT"/>'></var>
<var id="LABEL_CONTENT" title= '<fmt:message key="_LABEL_CONTENT"/>'></var>
<var id="LABEL_COMMENT" title= '<fmt:message key="_LABEL_COMMENT"/>'></var>
<var id="LABEL_NAME" title='<fmt:message key="_LABEL_NAME"/>'></var>
<var id="LABEL_EMAIL" title='<fmt:message key="_LABEL_EMAIL"/>'></var>
<var id="LABEL_URL" title='<fmt:message key="_LABEL_URL"/>'></var>
<var id="BUTTON_EMAIL" title= '<fmt:message key="_BUTTON_EMAIL"/>'></var>
<var id="BUTTON_CHAT" title= '<fmt:message key="_BUTTON_CHAT"/>'></var>
<var id="BUTTON_MESSAGE" title= '<fmt:message key="_BUTTON_MESSAGE"/>'></var>
<var id="BUTTON_ADD_BUDDY" title= '<fmt:message key="_BUTTON_ADD_BUDDY"/>'></var>
<var id="BUTTON_DELETE" title= '<fmt:message key="_BUTTON_DELETE"/>'></var>
<var id="BUTTON_CANCEL" title= '<fmt:message key="_BUTTON_CANCEL"/>'></var>
<var id="BUTTON_SEND" title= '<fmt:message key="_BUTTON_SEND"/>'></var>
<var id="BUTTON_OK" title= '<fmt:message key="_BUTTON_OK"/>'></var>
<var id="BUTTON_SAVE" title='<fmt:message key="_BUTTON_SAVE"/>'></var>
<var id="BUTTON_PREVIOUS" title='<fmt:message key="portlet.label.previous"/>'></var>
<var id="BUTTON_NEXT" title='<fmt:message key="portlet.label.next"/>'></var>
<var id="TITLE_CHAT" title= '<fmt:message key="_TITLE_CHAT"/>'></var>
<var id="TITLE_LANGUAGE" title= '<fmt:message key="_TITLE_LANGUAGE"/>'></var>
<var id="TITLE_SEARCH_FILTERS" title= '<fmt:message key="_TITLE_SEARCH_FILTERS"/>'></var>
<var id="TITLE_FEEDBACK" title= '<fmt:message key="_TITLE_FEEDBACK"/>'></var>
<var id="DELETE_BLOG" title='<fmt:message key="_DELETE_BLOG"/>'></var>
<var id="COMMAND_CLOSE_TAB" title= '<fmt:message key="_COMMAND_CLOSE_TAB"/>'></var>
<var id="COMMAND_CLOSE_PORTLET" title= '<fmt:message key="_COMMAND_CLOSE_PORTLET"/>'></var>
<var id="COMMAND_CLOSE_POPUP_PORTLET" title= '<fmt:message key="_COMMAND_CLOSE_POPUP_PORTLET"/>'></var>
<var id="COMMAND_CLOSE_POPUP_CHAT" title= '<fmt:message key="_COMMAND_CLOSE_POPUP_CHAT"/>'></var>
<var id="COMMAND_DELETE_CALENDAR_EVENT" title= '<fmt:message key="_COMMAND_DELETE_CALENDAR_EVENT"/>'></var>
<var id="COMMAND_DELETE_CALENDAR_EVENTS" title= '<fmt:message key="_COMMAND_DELETE_CALENDAR_EVENTS"/>'></var>
<var id="ERROR_PASSWORD_NOT_EQUAL" title= '<fmt:message key="_ERROR_PASSWORD_NOT_EQUAL"/>'></var>
<var id="ERROR_FIELDS_REQUIRED" title= '<fmt:message key="_ERROR_FIELDS_REQUIRED"/>'></var>
<var id="ERROR_ACCEPT_TERM_PRIVACY" title= '<fmt:message key="_ERROR_ACCEPT_TERM_PRIVACY"/>'></var>
<var id="ERROR_MINUTE_RATE" title= '<fmt:message key="_ERROR_MINUTE_RATE"/>'></var>
<var id="ERROR_EMAIL_FORMAT" title= '<fmt:message key="_ERROR_EMAIL_FORMAT"/>'></var>
<var id="ERROR_EMAIL_DUPLICATED" title= '<fmt:message key="_ERROR_EMAIL_DUPLICATED"/>'></var>
<var id="ERROR_URI_FORMAT" title= '<fmt:message key="_ERROR_URI_FORMAT"/>'></var>
<var id="ERROR_URI_DUPLICATED" title= '<fmt:message key="_ERROR_URI_DUPLICATED"/>'></var>
<var id="ERROR_PASSWORD_FORMAT" title= '<fmt:message key="_ERROR_PASSWORD_FORMAT"/>'></var>
<var id="ERROR_MESSAGE_SUBSCRIBE" title= '<fmt:message key="_ERROR_MESSAGE_SUBSCRIBE"/>'></var>

<var id="SUCCESS_MESSAGE_SUBSCRIBE" title= '<fmt:message key="_SUCCESS_MESSAGE_SUBSCRIBE"/>'></var>

<var id="MESSAGE_COMMENT_NEED_APPROVE" title= '<fmt:message key="_MESSAGE_COMMENT_NEED_APPROVE"/>'></var>
</fmt:bundle>