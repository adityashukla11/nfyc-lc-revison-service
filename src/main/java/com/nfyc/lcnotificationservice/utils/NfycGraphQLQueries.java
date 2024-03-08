package com.nfyc.lcnotificationservice.utils;

public class NfycGraphQLQueries {
  public static final String GET_DAILY_CONTEST_INFO = """
      query {
          activeDailyCodingChallengeQuestion {
            date
            link
            question {
              questionId
              questionFrontendId
              boundTopicId
              title
              titleSlug
            }
          }
      }
      """;

  public static final String GET_RECENT_AC_SUBMISSION = """
      query ($username: String!, $limit: Int) {
          recentAcSubmissionList(username: $username, limit: $limit) {
              title
              titleSlug
              timestamp
              statusDisplay
              lang
          }
      }  
      """;
}