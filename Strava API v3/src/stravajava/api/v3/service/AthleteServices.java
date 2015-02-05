package stravajava.api.v3.service;

import java.util.List;

import stravajava.api.v3.model.StravaAthlete;
import stravajava.api.v3.model.StravaSegmentEffort;
import stravajava.api.v3.model.reference.StravaGender;
import stravajava.api.v3.service.exception.NotFoundException;
import stravajava.api.v3.service.exception.UnauthorizedException;
import stravajava.util.Paging;

/**
 * StravaAthlete related services
 * 
 * @see <a href="http://strava.github.io/api/v3/athlete/">http://strava.github.io/api/v3/athlete/</a>
 * @author Dan Shannon
 *
 */
public interface AthleteServices {
	/**
	 * <p>This request is used to retrieve information about the currently authenticated {@link StravaAthlete athlete}.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/athlete</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/athlete/"/>http://strava.github.io/api/v3/athlete/</a>
	 * @return Returns a detailed representation of the {@link StravaAthlete athlete} 
	 */
	public StravaAthlete getAuthenticatedAthlete() throws UnauthorizedException;
	
	/**
	 * <p>This request is used to retrieve information about any {@link StravaAthlete athlete} on Strava.</p>
	 * 
	 * <p>Returns <code>null</code> if athlete does not exist</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/athletes/:id</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/athlete/"/>http://strava.github.io/api/v3/athlete/</a>
	 * 
	 * @param id The id of the {@link StravaAthlete athlete} to be returned
	 * @return Returns a summary representation of the {@link StravaAthlete athlete} even if the indicated athlete matches the authenticated athlete.
	 * @throws UnauthorizedException 
	 */
	public StravaAthlete getAthlete(Integer id) throws UnauthorizedException;
	
	/**
	 * <p>Updates the personal details of the currently authenticated {@link StravaAthlete athlete}.</p>
	 * 
	 * <p>Requires write permissions, as requested during the authorization process.</p>
	 * 
	 * <p>Only updates city, state, country, gender (sex) and weight.</p>
	 * 
	 * <p>URL PUT https://www.strava.com/api/v3/athlete</p>
	 *
	 * @see <a href="http://strava.github.io/api/v3/athlete/">http://strava.github.io/api/v3/athlete/</a>
	 * 
	 * @throws NotFoundException If the athlete does not exist (this is almost impossible, but just in case the athlete has somehow removed themselves from Strava altogether)
	 * @throws UnauthorizedException If the security token in use does not have write access
	 * @return Detailed representation of the updated athlete
	 */
	public StravaAthlete updateAuthenticatedAthlete(String city, String state, String country, StravaGender sex, Float weight) throws UnauthorizedException, NotFoundException;
	
	/**
	 * <p>Returns an array of {@link StravaSegmentEffort segment efforts} representing KOMs/QOMs and course records held by the given athlete.</p>
	 * 
	 * <p>Results are sorted by date, newest first.</p>
	 * 
	 * <p>Pagination is not supported.</p>
	 * 
	 * <p>Returns <code>null</code> if athlete with the given id is not found.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/athletes/:id/koms</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/athlete/">http://strava.github.io/api/v3/athlete/</a>
	 * 
	 * @param id The id of the {@link StravaAthlete athlete} whose KOM's are to be returned
	 * @return Returns an array of {@link StravaSegmentEffort segment effort} summary representations
	 */
	public List<StravaSegmentEffort> listAthleteKOMs(Integer id);
	
	/**
	 * <p>Returns an array of {@link StravaSegmentEffort segment efforts} representing KOMs/QOMs and course records held by the given athlete.</p>
	 * 
	 * <p>Results are sorted by date, newest first.</p>
	 * 
	 * <p>Pagination is supported.</p>
	 * 
	 * <p>Returns <code>null</code> if athlete with the given id is not found.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/athletes/:id/koms</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/athlete/">http://strava.github.io/api/v3/athlete/</a>
	 * 
	 * @param id The id of the {@link StravaAthlete athlete} whose KOM's are to be returned
	 * @param pagingInstruction (Optional) The page to be returned
	 * @return Returns an array of {@link StravaSegmentEffort segment effort} summary representations
	 */
	public List<StravaSegmentEffort> listAthleteKOMs(Integer id, Paging pagingInstruction);
	
	/**
	 * <p>Friends are users the current {@link StravaAthlete athlete} is following. The activities owned by these users will appear in the current athlete�s activity feed.</p>
	 * 
	 * <p>This request is for the authenticated athlete's friends.</p>
	 * 
	 * <p>Pagination is not supported.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/athletes/friends</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/follow/">http://strava.github.io/api/v3/follow/</a>
	 * 
	 * @return Returns an array of {@link StravaAthlete athlete} summary representations.
	 */
	public List<StravaAthlete> listAuthenticatedAthleteFriends();
	
	/**
	 * <p>Friends are users the current {@link StravaAthlete athlete} is following. The activities owned by these users will appear in the current athlete�s activity feed.</p>
	 * 
	 * <p>This request is for the authenticated athlete's friends.</p>
	 * 
	 * <p>Pagination is supported.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/athletes/friends</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/follow/">http://strava.github.io/api/v3/follow/</a>
	 * 
	 * @param pagingInstruction (Optional) The page to be returned
	 * @return Returns an array of {@link StravaAthlete athlete} summary representations.
	 */
	public List<StravaAthlete> listAuthenticatedAthleteFriends(Paging pagingInstruction);
	
	/**
	 * <p>Friends are users an {@link StravaAthlete athlete} is following. The activities owned by these users will appear in the current athlete�s activity feed.</p>
	 * 
	 * <p>If the indicated athlete has blocked the authenticated athlete, the result will be an empty array.</p> 
	 * 
	 * <p>Pagination is supported.</p>
	 * 
	 * <p>Returns <code>null</code> if athlete with the given id is not found.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/athletes/:id/friends</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/follow/">http://strava.github.io/api/v3/follow/</a>
	 * 
	 * @param id The id of the {@link StravaAthlete athlete} whose friends are to be listed
	 * @param pagingInstruction (Optional) The page to be returned
	 * @return List of {@link StravaAthlete athletes} who are friends of the identified athlete. Will be empty if the identified athlete has blocked the currently authenticated athlete.
	 */
	public List<StravaAthlete> listAthleteFriends(Integer id, Paging pagingInstruction);
	
	/**
	 * <p>Friends are users an {@link StravaAthlete athlete} is following. The activities owned by these users will appear in the current athlete�s activity feed.</p>
	 * 
	 * <p>If the indicated athlete has blocked the authenticated athlete, the result will be an empty array.</p> 
	 * 
	 * <p>Pagination is not supported.</p>
	 * 
	 * <p>Returns <code>null</code> if athlete with the given id is not found.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/athletes/:id/friends</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/follow/">http://strava.github.io/api/v3/follow/</a>
	 * 
	 * @param id The id of the {@link StravaAthlete athlete} whose friends are to be listed
	 * @return List of {@link StravaAthlete athletes} who are friends of the identified athlete. Will be empty if the identified athlete has blocked the currently authenticated athlete.
	 */
	public List<StravaAthlete> listAthleteFriends(Integer id);
	
	/**
	 * <p>Retrieve the {@link StravaAthlete athletes} who both the authenticated athlete and the indicated athlete are following.</p>
	 * 
	 * <p>Pagination is not supported.</p>
	 * 
	 * <p>Returns <code>null</code> if athlete with the given id is not found.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/athletes/:id/both-following</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/follow/">http://strava.github.io/api/v3/follow/</a>
	 * 
	 * @param id The id of the {@link StravaAthlete athlete} for whom the list of mutual friends is to be generated
	 * @return Returns an array of {@link StravaAthlete athlete} summary representations.
	 */
	public List<StravaAthlete> listAthletesBothFollowing(Integer id);

	/**
	 * <p>Retrieve the {@link StravaAthlete athletes} who both the authenticated athlete and the indicated athlete are following.</p>
	 * 
	 * <p>Pagination is supported.</p>
	 * 
	 * <p>Returns <code>null</code> if athlete with the given id is not found.</p>
	 * 
	 * <p>URL GET https://www.strava.com/api/v3/athletes/:id/both-following</p>
	 * 
	 * @see <a href="http://strava.github.io/api/v3/follow/">http://strava.github.io/api/v3/follow/</a>
	 * 
	 * @param id The id of the {@link StravaAthlete athlete} for whom the list of mutual friends is to be generated
	 * @param pagingInstruction (Optional) The page to be returned
	 * @return Returns an array of {@link StravaAthlete athlete} summary representations.
	 */
	public List<StravaAthlete> listAthletesBothFollowing(Integer id, Paging pagingInstruction);
}