package com.google.api.services.drive;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * A JSON representation of a reply to a comment on a file in Google Drive.
 */
public class CommentReply {
	/**
	 * The user who wrote this reply.
	 */
	public final User author;

	/**
	 * The plain text content used to create this reply. This is not HTML safe
	 * and should only be used as a starting point to make edits to a reply's
	 * content. This field is required on inserts if no verb is specified
	 * (resolve/reopen).
	 */
	public final String content;

	/**
	 * The date when this reply was first created.
	 */
	public final String createdDate;

	/**
	 * Whether this reply has been deleted. If a reply has been deleted the
	 * content will be cleared and this will only represent a reply that once
	 * existed.
	 */
	public final boolean deleted;

	/**
	 * HTML formatted content for this reply.
	 */
	public final String htmlContent;

	/**
	 * This is always drive#commentReply.
	 */
	public final String kind;

	/**
	 * The date when this reply was last modified.
	 */
	public final String modifiedDate;

	/**
	 * The ID of the reply.
	 */
	public final String replyId;

	/**
	 * The action this reply performed to the parent comment. When creating a
	 * new reply this is the action to be perform to the parent comment.
	 * Possible values are: - "resolve" - To resolve a comment. - "reopen" - To
	 * reopen (un-resolve) a comment.
	 */
	public final String verb;

	@JsonCreator
	public CommentReply(@JsonProperty("author") final User author,
			@JsonProperty("content") final String content,
			@JsonProperty("createdDate") final String createdDate,
			@JsonProperty("deleted") final boolean deleted,
			@JsonProperty("htmlContent") final String htmlContent,
			@JsonProperty("kind") final String kind,
			@JsonProperty("modifiedDate") final String modifiedDate,
			@JsonProperty("replyId") final String replyId,
			@JsonProperty("verb") final String verb) {
		this.author = author;
		this.content = content;
		this.createdDate = createdDate;
		this.deleted = deleted;
		this.htmlContent = htmlContent;
		this.kind = kind;
		this.modifiedDate = modifiedDate;
		this.replyId = replyId;
		this.verb = verb;
	}

}
