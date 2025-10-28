import axios from 'axios';

// Get API URL from environment variable with fallback
const API_BASE_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080/api/v1';

// Create axios instance with default config
const apiClient = axios.create({
  baseURL: API_BASE_URL,
  timeout: 300000, // 5 minutes for large video uploads
  headers: {
    'Accept': 'application/json',
  },
});

// Add request interceptor for debugging
apiClient.interceptors.request.use(
  (config) => {
    console.log('API Request:', config.method.toUpperCase(), config.url);
    return config;
  },
  (error) => {
    console.error('Request Error:', error);
    return Promise.reject(error);
  }
);

// Add response interceptor for error handling
apiClient.interceptors.response.use(
  (response) => {
    console.log('API Response:', response.status, response.data);
    return response;
  },
  (error) => {
    console.error('Response Error:', error.response?.data || error.message);
    return Promise.reject(error);
  }
);

export const uploadVideo = async (uploadData, onProgress) => {
  const formData = new FormData();
  formData.append('title', uploadData.title);
  formData.append('description', uploadData.description);
  formData.append('file', uploadData.file);

  try {
    const response = await apiClient.post('/videos', formData, {
      headers: { 
        'Content-Type': 'multipart/form-data' 
      },
      onUploadProgress: (progressEvent) => {
        const progress = Math.round(
          (progressEvent.loaded * 100) / progressEvent.total
        );
        onProgress(progress);
      },
    });
    return response.data;
  } catch (error) {
    console.error('Upload failed:', error);
    throw error;
  }
};

export const getVideoById = async (videoId) => {
  try {
    const response = await apiClient.get(`/videos/${videoId}`);
    return response.data;
  } catch (error) {
    console.error('Failed to fetch video:', error);
    throw error;
  }
};

export const getAllVideos = async () => {
  try {
    const response = await apiClient.get('/videos');
    return response.data;
  } catch (error) {
    console.error('Failed to fetch videos:', error);
    throw error;
  }
};

export const getVideoStreamUrl = (videoId) => {
  return `${API_BASE_URL}/videos/${videoId}/master.m3u8`;
};

export { apiClient };