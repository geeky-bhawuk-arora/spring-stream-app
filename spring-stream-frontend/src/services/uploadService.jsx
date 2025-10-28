import axios from 'axios';

const API_BASE_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080/api/v1';

export const uploadVideo = async (uploadData, onProgress) => {
  const formData = new FormData();
  formData.append('title', uploadData.title);
  formData.append('description', uploadData.description);
  formData.append('file', uploadData.file);

  return axios.post(`${API_BASE_URL}/videos`, formData, {
    headers: { 'Content-Type': 'multipart/form-data' },
    onUploadProgress: (progressEvent) => {
      const progress = Math.round(
        (progressEvent.loaded * 100) / progressEvent.total
      );
      onProgress(progress);
    },
  });
};