using System;
using System.Collections;
using System.Collections.Generic;
using System.Text;
using UnityEngine;
using UnityEngine.Networking;

public class test : MonoBehaviour 
{
	string clientID = "***REMOVED***";
	string clientSecret = "***REMOVED***";

	string gamesScope = "https://www.googleapis.com/auth/games";
	string driveAppDataScope = "https://www.googleapis.com/auth/drive.appdata";
	string driveScope = "https://www.googleapis.com/auth/drive";

	string manualUri = "urn:ietf:wg:oauth:2.0:oob";

	string authenticationCodeEndpoint = "https://accounts.google.com/o/oauth2/v2/auth";
	string tokenEndpoint = "https://www.googleapis.com/oauth2/v4/token";
	string refreshEndpoint = "https://www.googleapis.com/oauth2/v4/token";
	
	string authCode = "4/GvW61juo39d8yv_utJtSkXSn5IpBy1-j38qLGq0DTFw";
	string accessToken = "ya29.GltBBX4LgCsR6FYMPl9smB4YZuVm89Yq5hE8udvGmpA8rcrJagzPXOrfBLnFX0IuSUwduG3YvEyh0tHnp8xcppz83uUSVis0utwQ0xctyZJCvvuPm9a9j-OrYwjT";
	string refreshToken = "1/NGavkuJhHms3lsqYq_kJMb9RrBBy4eowaChDsVyUupo";

	string achievementsEndpoint = "https://www.googleapis.com/games/v1/achievements";

	string listEndpoint = "https://www.googleapis.com/drive/v3/files";


	IEnumerator Start () 
	{
		Debug.LogWarning(GoogleAuthCode());
		var request = Download();
		yield return request.SendWebRequest();
		if (request.isNetworkError || request.isHttpError)
		{
			Debug.LogError(request.error);
			Debug.LogError(request.responseCode);
		}
		else
		{
			Debug.LogWarning(request.downloadHandler.text);
		}
		yield return null;
	}
	
	string AddParameters(string endpoint, params string[] parameters)
	{
		return endpoint+"?"+string.Join("&", parameters);
	}

	string GoogleAuthCode()
	{
		return AddParameters(authenticationCodeEndpoint, 
		
			"client_id="+clientID,
			"scope="+gamesScope+" "+driveAppDataScope+" "+driveScope,
			"redirect_uri="+manualUri,
			"response_type=code"

		);
	}

	UnityWebRequest Token()
	{
		var form = new WWWForm();
		form.AddField("code",authCode);
		form.AddField("client_id",clientID);
		form.AddField("client_secret",clientSecret);
		form.AddField("grant_type","authorization_code");
		form.AddField("redirect_uri",manualUri);
		
		var request = UnityWebRequest.Post(tokenEndpoint,form);
		return request;
	}

	UnityWebRequest Refresh()
	{
		var form = new WWWForm();
		form.AddField("refresh_token", refreshToken);
		form.AddField("client_id", clientID);
		form.AddField("client_secret", clientSecret);
		form.AddField("grant_type", "refresh_token");

		return UnityWebRequest.Post(refreshEndpoint, form);
	}

	UnityWebRequest Achievements()
	{
		var url = achievementsEndpoint;
		Debug.LogWarning(url);
		var request = UnityWebRequest.Get(url);
		AddAuthorization(request);
		return request;
	}

	UnityWebRequest List()
	{
		var url = AddParameters(listEndpoint, "spaces=appDataFolder");
		Debug.LogWarning(url);
		var request = UnityWebRequest.Get(url);
		AddAuthorization(request);
		return request;
	}

	UnityWebRequest Download()
	{
		var request = UnityWebRequest.Get("https://www.googleapis.com/drive/v3/files/1ts-zjR_xFnZ-GEogLi6JovEe1FjG7_Of?alt=media");
		AddAuthorization(request);

		return request;
	}

	UnityWebRequest Create()
	{
		var request = new UnityWebRequest("https://www.googleapis.com/drive/v3/files");
		var uploader = new UploadHandlerRaw(Encoding.ASCII.GetBytes("{\"name\":\"martin\"}"));
		uploader.contentType = "application/json";
		request.uploadHandler = uploader;
		request.downloadHandler = new DownloadHandlerBuffer();
       	request.method          = UnityWebRequest.kHttpVerbPOST;
		
		AddAuthorization(request);
		return request;
	}

	UnityWebRequest Save()
	{
		var request             = new UnityWebRequest("https://www.googleapis.com/upload/drive/v3/files?uploadType=media");
       	var uploader   = new UploadHandlerRaw(Encoding.ASCII.GetBytes("{\"parents\": \"hey\"}"));
		uploader.contentType = "application/json";
		request.uploadHandler = uploader;
       	request.downloadHandler = new DownloadHandlerBuffer();
       	request.method          = UnityWebRequest.kHttpVerbPOST;
		
		AddAuthorization(request);
		return request;

	}

	void AddAuthorization(UnityWebRequest request)
	{
		request.SetRequestHeader("Authorization", "Bearer "+accessToken);
	}
}


