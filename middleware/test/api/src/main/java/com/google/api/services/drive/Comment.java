package com.google.api.services.drive;

import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * A JSON representation of a comment on a file in Google Drive.
 */
public class Comment {
	/**
	 * A region of the document represented as a JSON string. See anchor
	 * documentation for details on how to define and interpret anchor
	 * properties.
	 */
	public final String anchor;

	/**
	 * The user who wrote this comment.
	 */
	public final User author;

	/**
	 * The ID of the comment.
	 */
	public final String commentId;

	/**
	 * The plain text content used to create this comment. This is not HTML safe
	 * and should only be used as a starting point to make edits to a comment's
	 * content.
	 */
	public final String content;

	/**
	 * The context of the file which is being commented on.
	 */
	public final Context context;

	/**
	 * The date when this comment was first created.
	 */
	public final String createdDate;

	/**
	 * Whether this comment has been deleted. If a comment has been deleted the
	 * content will be cleared and this will only represent a comment that once
	 * existed.
	 */
	public final boolean deleted;

	/**
	 * The file which this comment is addressing.
	 */
	public final String fileId;

	/**
	 * The title of the file which this comment is addressing.
	 */
	public final String fileTitle;

	/**
	 * HTML formatted content for this comment.
	 */
	public final String htmlContent;

	/**
	 * This is always drive#comment.
	 */
	public final String kind;

	/**
	 * The date when this comment or any of its replies were last modified.
	 */
	public final String modifiedDate;

	/**
	 * Replies to this post.
	 */
	public final List<CommentReply> replies;

	/**
	 * A link back to this comment.
	 */
	public final String selfLink;

	/**
	 * The status of this comment. Status can be changed by posting a reply to a
	 * comment with the desired status. - "open" - The comment is still open. -
	 * "resolved" - The comment has been resolved by one of its replies.
	 */
	public final String status;

	@JsonCreator
	public Comment(@JsonProperty("anchor") final String anchor,
			@JsonProperty("author") final User author,
			@JsonProperty("commentId") final String commentId,
			@JsonProperty("content") final String content,
			@JsonProperty("context") final Context context,
			@JsonProperty("createdDate") final String createdDate,
			@JsonProperty("deleted") final boolean deleted,
			@JsonProperty("fileId") final String fileId,
			@JsonProperty("fileTitle") final String fileTitle,
			@JsonProperty("htmlContent") final String htmlContent,
			@JsonProperty("kind") final String kind,
			@JsonProperty("modifiedDate") final String modifiedDate,
			@JsonProperty("replies") final List<CommentReply> replies,
			@JsonProperty("selfLink") final String selfLink,
			@JsonProperty("status") final String status) {
		this.anchor = anchor;
		this.author = author;
		this.commentId = commentId;
		this.content = content;
		this.context = context;
		this.createdDate = createdDate;
		this.deleted = deleted;
		this.fileId = fileId;
		this.fileTitle = fileTitle;
		this.htmlContent = htmlContent;
		this.kind = kind;
		this.modifiedDate = modifiedDate;
		this.replies = replies;
		this.selfLink = selfLink;
		this.status = status;
	}

	/**
	 * The context of the file which is being commented on.
	 */
	public static class Context {
		/**
		 * The MIME type of the context snippet.
		 */
		public final String type;

		/**
		 * Data representation of the segment of the file being commented on. In
		 * the case of a text file for example, this would be the actual text
		 * that the comment is about.
		 */
		public final String value;

		@JsonCreator
		public Context(@JsonProperty("type") final String type,
				@JsonProperty("value") final String value) {
			this.type = type;
			this.value = value;
		}

	}
}
